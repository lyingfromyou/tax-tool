package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.taxtool.entity.SendMailInfo;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/10/11
 */
@RestController
public class SendMailController {

    @Value("${spring.mail.username}")
    private String form;

    @Autowired
    private JavaMailSender mailSender;


    @PostMapping("/sendMail")
    public String sendMail(@RequestParam(value = "files", required = false) MultipartFile[] files,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String content) {
        if (ArrayUtil.isEmpty(files)) {
            return "没有文件";
        }

        if (StrUtil.isBlank(title)) {
            return "邮件主题不能为空";
        }

        if (StrUtil.isBlank(content)) {
            return "邮件内容不能为空";
        }


        Set<SendMailInfo> sendMailInfos = new HashSet<>();
        for (MultipartFile file : files) {
            try {
                ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
                reader.addHeaderAlias("姓名", "xm");
                reader.addHeaderAlias("邮箱", "mail");
                List<SendMailInfo> infos = reader.readAll(SendMailInfo.class);
                if (CollUtil.isNotEmpty(infos)) {
                    sendMailInfos.addAll(infos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (CollUtil.isNotEmpty(sendMailInfos)) {
            List<SimpleMailMessage> messageList = buildMailMessages(title, content, sendMailInfos);

            new Thread(() ->
                    mailSender.send(messageList.toArray(new SimpleMailMessage[messageList.size()]))
            ).start();


        } else {
            return "文件内容为空, 或解析文件失败";
        }

        return "ok";
    }


    private List<SimpleMailMessage> buildMailMessages(final String title, final String content, Set<SendMailInfo> sendMailInfos) {


        sendMailInfos.stream().filter(sendMailInfo ->
                StrUtil.isNotBlank(sendMailInfo.getMail()) && StrUtil.isNotBlank(sendMailInfo.getXm()))
                .map(sendMailInfo -> {
                    SimpleMailMessage message = new SimpleMailMessage();


                    message.setFrom(form);
                    message.setTo(sendMailInfo.getMail());
                    message.setSubject(title);
                    message.setText(content);


                    return message;
                }).collect(Collectors.toList());


        return new ArrayList<>();
    }


    public static void main(String[] args) throws IOException, MailjetSocketTimeoutException, MailjetException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(
                "421f456e32ea5567fb56be9fb5847d1a",
                "dde6f6ded459f2ce4f1469e2a428acb5",
                new ClientOptions("v3.1"));


        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "liqian0213@gmail.com")
                                        .put("Name", "notice_template"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "183023840@qq.com")
                                                .put("Name", "passenger 1")))
                                .put(Emailv31.Message.TEMPLATEID, 1039708)
                                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                .put(Emailv31.Message.SUBJECT, "通知")
                                .put(Emailv31.Message.VARIABLES, new JSONObject()
                                        .put("name", "李乾"))));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }


}
