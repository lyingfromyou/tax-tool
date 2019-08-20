package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.taxtool.entity.InputUserInfo;
import com.example.taxtool.task.GetTaskResultUserList;
import com.example.taxtool.task.GetUserInfoTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Controller
public class TaxController {

    BlockingQueue blockingQueue = new ArrayBlockingQueue<>(15);
    ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, blockingQueue);

    @PostMapping(value = "/upload", produces = "application/json; charset=utf-8")
    @ResponseBody
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
            reader.addHeaderAlias("客户姓名", "xm");
            reader.addHeaderAlias("身份证号", "sfz");
            List<InputUserInfo> inputUserInfos = reader.readAll(InputUserInfo.class);

            List<List<InputUserInfo>> splitList = CollUtil.split(inputUserInfos, 100);

            Collection<GetUserInfoTask> callables = new ArrayList<>();
            for (int index = 0; index < splitList.size(); index++) {
                List<InputUserInfo> infos = splitList.get(index);
                callables.add(new GetUserInfoTask(cookie, infos, index));
            }
            new Thread(new GetTaskResultUserList(callables, threadPoolExecutor, fileName)).start();
            return "ok";
        } else {
            return "说好的文件呢";
        }
    }

}