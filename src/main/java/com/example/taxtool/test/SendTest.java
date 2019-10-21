package com.example.taxtool.test;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.SmtpApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by Lying
 * @Date 2019/10/14
 */
public class SendTest {


    public static void main(String[] args) throws Exception {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey("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");


        SmtpApi smtpApi = new SmtpApi();
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();




        sendSmtpEmail.setTemplateId(2L);
        Map<String, String> params = new HashMap<>();
        params.put("userName", "李乾");
        sendSmtpEmail.setParams(params);

        List<SendSmtpEmailTo> toList = new ArrayList<>();
        SendSmtpEmailTo to1 = new SendSmtpEmailTo();
        to1.setEmail("183023840@qq.com");
        toList.add(to1);
        sendSmtpEmail.setTo(toList);


        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail("liqian0213@gmail.com");
        sendSmtpEmail.setSender(sender);



        CreateSmtpEmail smtpEmail = smtpApi.sendTransacEmail(sendSmtpEmail);
        System.err.println(smtpEmail);



    }

    @Test
    public void test2(){
        String s = "{姓名} , 年龄: {年龄}, 工资: {工资}";
        Map<String, String> map = new HashMap<>();
        map.put("姓名", "lq");
        map.put("年龄", "25");
        map.put("工资", "11111");

        System.err.println(StrUtil.format(s,map));
    }

}
