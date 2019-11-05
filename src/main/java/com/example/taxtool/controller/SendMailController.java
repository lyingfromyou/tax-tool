package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.entity.MailInfo;
import com.example.taxtool.entity.SendMailInfo;
import com.example.taxtool.utils.CheckMailUtil;
import com.example.taxtool.utils.CommonConstants;
import com.example.taxtool.utils.GsonUtil;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/10/11
 */
@RestController
@RequestMapping("/mail")
public class SendMailController {

    @Value("${mailjet.from}")
    private String from;
    @Value("${mailjet.key}")
    private String key;
    @Value("${mailjet.secret}")
    private String secret;

    @PostMapping(value = "/send", produces = "application/json; charset=utf-8")
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
                File localFile = FileUtil.writeFromStream(file.getInputStream(), CommonConstants.SEND_MAIL_FILE_PATH + fileName);
                ExcelReader reader = ExcelUtil.getReader(localFile);
                List<Map<String, Object>> infos = reader.readAll();
                for (Map<String, Object> row : infos) {
                    String str = StrUtil.format(content, row);
                    String mail = row.get("邮箱").toString();
                    sendMailInfos.add(new SendMailInfo(str, mail));
                }
                FileUtil.del(localFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return doSend(title, name, sendMailInfos);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/verification")
    public String verification(@RequestParam("file") MultipartFile file) throws IOException {
        if (null != file && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            System.err.println("上传文件名: " + fileName);
            File localFile = FileUtil.writeFromStream(file.getInputStream(),
                    CommonConstants.VERIFICATION_MAIL_FILE_PATH + fileName);

            ExcelReader reader = ExcelUtil.getReader(localFile);
            reader.addHeaderAlias("邮箱", "mail");
            List<MailInfo> mailInfos = reader.readAll(MailInfo.class);
            if (CollUtil.isNotEmpty(mailInfos)) {
                if (mailInfos.size() > 200) {
                    FileUtil.del(localFile.getAbsolutePath());
                    return "邮箱不能大于200个";
                }

                String beforeId = stringRedisTemplate.opsForValue().get(CommonConstants.UPLOAD_FILE_ID_KEY);
                String id;
                if (StrUtil.isBlank(beforeId)) {
                    id = "1";
                } else {
                    id = String.valueOf(Long.valueOf(beforeId) + 1);
                }
                stringRedisTemplate.opsForValue().set(CommonConstants.UPLOAD_FILE_ID_KEY, id);

                String resultFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "check_result.xlsx";
                new Thread(() -> {
                    Set<String> mails = mailInfos.stream().map(MailInfo::getMail).collect(Collectors.toSet());
                    List<MailInfo> checkResult = CheckMailUtil.batchCheck(mails);

                    ExcelWriter writer = ExcelUtil.getWriter(CommonConstants.FILE_UPLOAD_PATH + id + StrUtil.SLASH
                            + resultFileName);
                    writer.addHeaderAlias("mail", "邮箱");
                    writer.addHeaderAlias("isExist", "是否存在");

                    // 一次性写出内容
                    writer.write(checkResult, true);
                    // 关闭writer，释放内存
                    writer.close();

                }).start();
                return "129.28.131.210/file/download?fileId=" + id + StrUtil.CRLF + "验证时间较长, 请过3-5分钟后再进行下载";
            }
        }
        return "没有数据";
    }


    private String doSend(final String title, final String name, Set<SendMailInfo> sendMailInfos) throws Exception {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(key, secret, new ClientOptions("v3.1"));

        StringBuilder builder = new StringBuilder();
        builder.append("200 代表 成功, 其他的就是有问题");
        builder.append(StrUtil.CRLF);
        builder.append("-------------------------------------");
        builder.append(StrUtil.CRLF);

        for (List<SendMailInfo> splitList : CollUtil.split(sendMailInfos, 50)) {
            JSONArray sendMsgs = new JSONArray();
            request = new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES, sendMsgs);

            for (SendMailInfo sendMailInfo : splitList) {
                sendMsgs.put(new JSONObject()
                        .put(Emailv31.Message.FROM, new JSONObject()
                                .put(Emailv31.Message.EMAIL, from)
                                .put(Emailv31.Message.NAME, name)
                        )
                        .put(Emailv31.Message.TO, new JSONArray()
                                .put(new JSONObject()
                                        .put(Emailv31.Message.EMAIL, sendMailInfo.getMail())
                                ))
                        .put(Emailv31.Message.SUBJECT, title)
                        .put(Emailv31.Message.TEXTPART, sendMailInfo.getContent()));
            }
            response = client.post(request);
            GsonUtil.dump(response);

            builder.append(response.getStatus());
            builder.append(StrUtil.CRLF);
            builder.append(GsonUtil.toJson(response.getData()));
            builder.append(StrUtil.CRLF);
            builder.append(StrUtil.CRLF);
        }
        return builder.toString();
    }


}
