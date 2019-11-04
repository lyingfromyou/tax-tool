package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.taxtool.entity.InputUserInfo;
import com.example.taxtool.task.GetTaskResultUserList;
import com.example.taxtool.task.GetUserInfoTask;
import com.example.taxtool.task.RemoveAndDeleteUser;
import com.example.taxtool.utils.BalanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


@RestController
public class UserCompanyInfoController {

    @Autowired
    ThreadPoolExecutor executor;

    @PostMapping(value = "/handleCompanyInfo", produces = "application/json; charset=utf-8")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String cookie) throws IOException {
        if (file != null && !file.isEmpty()) {
            if (StrUtil.isBlank(cookie)) {
                return "爸爸的 cookie 呢?";
            }

            String originalFilename = file.getOriginalFilename();
            String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            System.err.println(fileName);

            System.err.println(cookie);
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            reader.addHeaderAlias("姓名", "xm");
            reader.addHeaderAlias("身份证", "sfz");
            List<InputUserInfo> inputUserInfos = reader.readAll(InputUserInfo.class);

            if (CollUtil.isNotEmpty(inputUserInfos) && inputUserInfos.size() > 1000) {
                return "不要超过1000条数据";
            }

            List<List<InputUserInfo>> splitList = BalanceUtil.balance(inputUserInfos);
            Collection<GetUserInfoTask> callables = new ArrayList<>();
            for (int index = 0; index < splitList.size(); index++) {
                List<InputUserInfo> infos = splitList.get(index);
                callables.add(new GetUserInfoTask(cookie, infos, index));
            }
            new Thread(new GetTaskResultUserList(callables, executor, fileName)).start();
            return "ok";
        } else {
            return "说好的文件呢";
        }
    }

    @PostMapping("/batchDelExistUserInfo")
    public String batchDelete(@RequestParam(required = false) String cookie){
        if (StrUtil.isBlank(cookie)) {
            return "爸的 cookie 呢?";
        }
        new Thread(new RemoveAndDeleteUser(cookie, executor)).start();
        return "ok";
    }

}