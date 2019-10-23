package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.taxtool.entity.SendMailInfo;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author by Lying
 * @Date 2019/10/11
 */
@RestController
public class SendMailController {

    @Value("${mailjet.from}")
    private String from;
    @Value("${mailjet.key}")
    private String key;
    @Value("${mailjet.secret}")
    private String secret;

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam(value = "files", required = false) MultipartFile[] files,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String content) throws Exception {
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
                String fileName = file.getOriginalFilename();
                File localFile = FileUtil.writeFromStream(file.getInputStream(), "/tmp/send_mail_file/" + fileName);
                ExcelReader reader = ExcelUtil.getReader(localFile);
                List<Map<String, Object>> infos = reader.readAll();
                for (Map<String, Object> row : infos) {
                    String str = StrUtil.format(content, row);
                    String mail = row.get("邮箱").toString();
                    sendMailInfos.add(new SendMailInfo(str, mail));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return doSend(title, name, sendMailInfos);
    }


    private String doSend(final String title, final String name, Set<SendMailInfo> sendMailInfos) throws Exception {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(key, secret, new ClientOptions("v3.1"));

        StringBuilder builder = new StringBuilder();
        builder.append("200 代表 成功, 其他的就是有问题");
        builder.append("-------------------------------------");
        builder.append(StrUtil.CRLF);

        for (List<SendMailInfo> splitList : CollUtil.split(sendMailInfos, 50)) {
            JSONArray sendMsgs = new JSONArray();
            request = new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES, sendMsgs);

            for (SendMailInfo sendMailInfo : splitList) {
                sendMsgs.put(new JSONObject()
                        .put(Emailv31.Message.FROM, new JSONObject()
                                .put("Email", from)
                                .put("Name", name)
                        )
                        .put(Emailv31.Message.TO, new JSONArray()
                                .put(new JSONObject()
                                        .put("Email", sendMailInfo.getMail())
                                ))
                        .put(Emailv31.Message.SUBJECT, title)
                        .put(Emailv31.Message.TEXTPART, sendMailInfo.getContent()));
            }
            response = client.post(request);
            System.out.println(response.getStatus());
            System.out.println(response.getData());
            builder.append(response.getData());
            builder.append(StrUtil.CRLF);
            builder.append(StrUtil.CRLF);
        }
        return builder.toString();
    }


}
