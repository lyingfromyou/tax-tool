package com.example.taxtool.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.entity.InputUserInfo;
import com.example.taxtool.entity.OutputUserInfo;
import com.example.taxtool.entity.UserInfo;
import com.example.taxtool.utils.TaxUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ThymeleafController {

    @GetMapping("/show")
    public String show() {
        return "show";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam String cookie) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        System.err.println(cookie);
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        reader.addHeaderAlias("客户姓名", "xm");
        reader.addHeaderAlias("身份证号", "sfz");

        List<InputUserInfo> inputUserInfos = reader.readAll(InputUserInfo.class);

        String fileName = file.getOriginalFilename().replaceFirst("\\.xlsx", "");
        new Thread(new Task(cookie, inputUserInfos, fileName)).start();
        return "ok";
    }


}

class Task implements Runnable {

    private String cookie;
    private String fileName;
    private List<InputUserInfo> inputUserInfos;

    public Task(String cookie, List<InputUserInfo> inputUserInfos,String fileName) {
        this.cookie = cookie;
        this.inputUserInfos = inputUserInfos;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        List<OutputUserInfo> userInfos = new ArrayList<>();
        for (InputUserInfo inputUserInfo : inputUserInfos) {
            if (TaxUtil.create(cookie, inputUserInfo.getXm(), inputUserInfo.getSfz())) {
                System.err.println(inputUserInfo.getXm() + " -- 添加成功");
                UserInfo userInfo = TaxUtil.query(cookie);
                userInfos.add(new OutputUserInfo(userInfo));
                if (TaxUtil.remove(cookie, userInfo)) {
                    System.err.println(inputUserInfo.getXm() + " -- 解除授权成功");

                    if (TaxUtil.delete(cookie, userInfo)) {
                        System.err.println(inputUserInfo.getXm() + " -- 删除用户成功");
                    } else {
                        System.err.println(inputUserInfo.getXm() + " -- 删除用户失败");
                    }
                } else {
                    System.err.println(inputUserInfo.getXm() + " -- 解除授权失败");
                }
            } else {
                System.err.println(inputUserInfo.getXm() + " -- 添加失败");
            }
        }

        ExcelWriter writer = ExcelUtil.getWriter("D:\\" + LocalDate.now() + StrUtil.DASHED + fileName + "-tax.xlsx");
        writer.addHeaderAlias("xm", "姓名");
        writer.addHeaderAlias("sfz", "身份证");
        writer.addHeaderAlias("company", "公司");

        // 一次性写出内容
        writer.write(userInfos, true);

        // 关闭writer，释放内存
        writer.close();
    }

}