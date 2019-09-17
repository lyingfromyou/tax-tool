package com.example.taxtool.task;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.config.SaveFilePath;
import com.example.taxtool.entity.OutputUserInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/8/18
 */
public class GetTaskResultUserList implements Runnable {

    private Collection<GetUserInfoTask> callables;
    private ThreadPoolExecutor threadPoolExecutor;
    private String fileName;

    public GetTaskResultUserList(Collection<GetUserInfoTask> callables, ThreadPoolExecutor threadPoolExecutor, String fileName) {
        this.callables = callables;
        this.threadPoolExecutor = threadPoolExecutor;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Long start = System.currentTimeMillis();
        List<OutputUserInfo> userInfos = new ArrayList<>();
        try {
            List<Future<List<OutputUserInfo>>> futureList = threadPoolExecutor.invokeAll(this.callables);
            for (Future<List<OutputUserInfo>> future : futureList) {
                if (future.isDone()) {
                    userInfos.addAll(future.get());
                }
            }
            System.err.println(userInfos.size());
            System.err.println(userInfos.stream().collect(Collectors.toSet()).size());
            System.err.println("共花费: " + (System.currentTimeMillis() - start) + "  毫秒!");

//            this.threadPoolExecutor.shutdown();

            ExcelWriter writer = ExcelUtil.getWriter(SaveFilePath.FILE_PATH + this.fileName + StrUtil.DASHED + LocalDate.now()+ ".xlsx");
            writer.addHeaderAlias("xm", "姓名");
            writer.addHeaderAlias("sfz", "身份证");
            writer.addHeaderAlias("company", "公司");

            // 一次性写出内容
            writer.write(userInfos, true);
            // 关闭writer，释放内存
            writer.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
