package com.example.taxtool.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class MailUtil {
    @Value("${spring.mail.username}")
    private String form;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * @param to      接收者地址
     * @param subject 主题
     * @param content 邮件内容
     */
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发起者
        mailMessage.setFrom(form);
        //接受者
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            log.debug("start sen mail");
            mailSender.send(mailMessage);
            log.info(LocalDateTime.now() + " -> send simple email to " + to + ", success");
        } catch (Exception e) {
            log.error(LocalDateTime.now() + " -> send simple email to " + to + ", error  , msg: " + e.getMessage());
        }
    }

    /**
     * @param to       接收者地址
     * @param subject  主题
     * @param content  邮件内容
     * @param filePath 附件文件地址
     */
    public void sendMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(form);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            if (StrUtil.isNotBlank(filePath)) {
                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                helper.addAttachment(fileName, file);
            }
            //添加多个附件可以使用多条
            mailSender.send(message);
            log.info(LocalDateTime.now() + " -> send appendix email to " + to + ", success");
        } catch (Exception e) {
            e.printStackTrace();
            log.info(LocalDateTime.now() + " -> send appendix email to " + to + ", error");
        }
    }

    public void sendMail(String to, String subject, String content, String filePath, String sourceName) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(form);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            if (StrUtil.isNotBlank(filePath)) {
                helper.addAttachment(StrUtil.isNotBlank(sourceName) ? sourceName : UUID.randomUUID().toString(), new FileUrlResource(filePath));
            }
            //添加多个附件可以使用多条
            mailSender.send(message);
            log.info(LocalDateTime.now() + " -> send appendix email to " + to + ", success");
        } catch (Exception e) {
            e.printStackTrace();
            log.info(LocalDateTime.now() + " -> send appendix email to " + to + ", error");
        }
    }

    public void sendMail(String to, String subject, String content, File... files) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(form);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content);
			if (ArrayUtil.isNotEmpty(files)) {
				for (File file : files) {
					String fileName = FileUtil.getName(file);
					helper.addAttachment(fileName, file);
				}
			}
			mailSender.send(message);
			log.info(LocalDateTime.now() + " -> send appendix email to " + to + ", success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LocalDateTime.now() + " -> send appendix email to " + to + ", error");
		}

    }

}
