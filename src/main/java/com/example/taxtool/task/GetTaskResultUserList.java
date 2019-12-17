package com.example.taxtool.task;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.entity.OutputUserInfo;
import com.example.taxtool.utils.CommonConstants;
import com.example.taxtool.utils.MailUtil;
import com.example.taxtool.utils.SpringUtil;

import java.io.File;
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
    private String email;

    public GetTaskResultUserList(Collection<GetUserInfoTask> callables, ThreadPoolExecutor threadPoolExecutor,
                                 String fileName, String email) {
        this.callables = callables;
        this.threadPoolExecutor = threadPoolExecutor;
        this.fileName = fileName;
        this.email = email;
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

            String saveFileName = this.fileName + StrUtil.DASHED + LocalDate.now() + ".xlsx";
            String saveFilePath = CommonConstants.BASE_PATH + CommonConstants.COMPANY_INFO_FILE_PATH + saveFileName;
            ExcelWriter writer = ExcelUtil.getWriter(saveFilePath);
            writer.addHeaderAlias("xm", "姓名");
            writer.addHeaderAlias("sfz", "身份证");
            writer.addHeaderAlias("company", "公司");

            // 一次性写出内容
            writer.write(userInfos, true);
            // 关闭writer，释放内存
            writer.close();

            String logPath=  CommonConstants.BASE_PATH + CommonConstants.TAX_HANDLE_LOG_PATH + fileName + StrUtil.SLASH;
            FileUtil.copy(saveFilePath, logPath + saveFileName, true);
            List<File> logs = FileUtil.loopFiles(logPath);
            MailUtil mailUtil = SpringUtil.getBean(MailUtil.class);
            mailUtil.sendMail(email,"过单位结果",
                    "邮件包含执行结果以及执行日志, 先查看日志, 如出现过多失败, 结果一定是失败, 很大可能是授权过期或者税网出现问题" +
                            ", 先查看税网是否能用, 以及是否出现过多未删除的授权账号, 再进行重试. ",
                    logs.toArray(new File[logs.size()]));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
