package com.example.taxtool;

import cn.hutool.core.io.FileUtil;
import com.example.taxtool.service.MinioTemplate;
import com.example.taxtool.utils.CheckMail;
import com.example.taxtool.utils.MailUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author by Lying
 * @Date 2019/10/22
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    public static final String key = "IPHONE_11_PRO";
    public static final CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void test99() throws Exception {
        redisTemplate.opsForValue().set(key, "10");
//        redisTemplate.opsForValue().set(key, 10);
//
        for (int i = 0; i < 10000; i++) {
            new Thread(new QiangGou(new User(Long.valueOf(i), i + " 用户"), redisTemplate)).start();
        }
        latch.countDown();
        TimeUnit.SECONDS.sleep(10);

    }

    static class QiangGou implements Runnable {
        private User user;
        private  StringRedisTemplate redisTemplate;


        public QiangGou(User user, StringRedisTemplate redisTemplate) {
            this.user = user;
            this.redisTemplate = redisTemplate;
        }

        @SneakyThrows
        @Override
        public void run() {
            latch.await();
            Long i = redisTemplate.opsForValue().decrement(key);
            if (i >= 0) {
                System.err.println(user + ": 抢到 ->" + i);
            }
        }
    }

    @Data
    @AllArgsConstructor
    class User {
        Long userId;
        String userName;
    }

    @Autowired
    MinioTemplate minioTemplate;

    @Test
    public void test1() {
        String fileName = "11.xlsx";
        try (InputStream inputStream = minioTemplate.getObject("tax", fileName)) {
            FileUtil.writeFromStream(inputStream, "D:\\tmp\\" + fileName);
        } catch (Exception e) {
            log.error("文件读取异常", e);
        }
    }

    @Test
    public void test2() {
        List<String> mails = CheckMail.check(Arrays.asList("984945343@qq.com", "941185245@qq.com"));
        System.err.println(mails);
    }


    @Autowired
    MailUtil mailUtil;

    @Test
    public void test3() {
        List<File> logs = FileUtil.loopFiles("C:\\Users\\Lying\\Desktop\\先");
        mailUtil.sendMail("183023840@qq.com", "无主题", "没内容", logs.toArray(new File[logs.size()]));
    }
}
