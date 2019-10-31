package com.example.taxtool.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author by Lying
 * @Date 2019/10/23
 */
@Component
public class CheckMail {

    //实例化CompletionService
    @Autowired
    private static CompletionService<String> completionService;
    private static final String API_URL = "http://www.mail-verifier.com/do/api";
    private static final String KEY = "A19B22069958C181B4AC0F50D9BB1903";

    public static List<String> check(List<String> emailList) {
        for (String email : emailList) {
            completionService.submit(doCheck(email));
        }
        List<String> existMails = new ArrayList<>();
        for (String email : emailList) {
            try {
                Future<String> doResult = completionService.take();
                String existEmail = doResult.get();
                if (StrUtil.isNotBlank(existEmail)) {
                    existMails.add(email);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        return existMails;
    }

    private static Callable<String> doCheck(String email) {
        return () -> {
            Map<String, String> params = new HashMap<>();
            params.put("key", KEY);
            params.put("verify", email);
            String resultStr = HttpUtil.get(API_URL, params);
            System.err.println(resultStr);
            CheckResult result = JSONUtil.toBean(resultStr, CheckResult.class);
            return Integer.valueOf(1).equals(result.getCode()) ? email : StrUtil.EMPTY;
        };
    }


    @Data
    public static class CheckResult {
        private String email; //目标邮箱
        private Integer code;               //结果号码
        private String msg;   //详细验证信息
        private Integer credits;       //剩余验证次数
    }


    @Autowired
    public void setCompletionService(Executor executor) {
        CheckMail.completionService = new ExecutorCompletionService<>(executor);
    }

}

