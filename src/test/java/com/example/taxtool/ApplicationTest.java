package com.example.taxtool;

import cn.hutool.core.io.FileUtil;
import com.example.taxtool.service.MinioTemplate;
import com.example.taxtool.utils.CheckMail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author by Lying
 * @Date 2019/10/22
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    MinioTemplate minioTemplate;

    @Test
    public void test1(){
        String fileName = "11.xlsx";
        try (InputStream inputStream = minioTemplate.getObject("tax", fileName)) {
            FileUtil.writeFromStream(inputStream, "D:\\tmp\\"+fileName);
        } catch (Exception e) {
            log.error("文件读取异常", e);
        }
    }

    @Test
    public void test2(){
            List<String> mails = CheckMail.check(Arrays.asList("984945343@qq.com", "941185245@qq.com"));
            System.err.println(mails);
    }
}
