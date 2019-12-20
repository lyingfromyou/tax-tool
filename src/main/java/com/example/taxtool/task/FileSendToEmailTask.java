package com.example.taxtool.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.utils.CommonConstants;
import com.example.taxtool.utils.MailUtil;
import com.example.taxtool.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author by Lying
 * @Date 2019/12/17
 */
@Slf4j
public class FileSendToEmailTask implements Runnable{

    private String email;
    private StringBuffer content;


    public FileSendToEmailTask(String email, StringBuffer content) {
        this.email = email;
        this.content = content;
    }

    @Override
    public void run() {
        log.info("\n, {} ===== file send to email start", LocalDateTime.now());

        ExcelWriter writer = ExcelUtil.getWriter(true);
        String[] lines = content.toString().split(StrUtil.CRLF);
        for (int i = 0; i < lines.length; i++) {
            String[] columns = lines[i].split(StrUtil.COMMA);
            System.err.println(Arrays.toString(columns));

            if (i == 0) {
                writer.writeHeadRow(CollUtil.newLinkedHashSet(columns));
            } else {
                writer.writeRow(CollUtil.newLinkedHashSet(columns));
            }
        }

        String savePath = CommonConstants.BASE_PATH
                + CommonConstants.FILE_UPLOAD_PATH
                + "content.xlsx";
        writer.flush(FileUtil.newFile(savePath));
        writer.close();

        MailUtil mailUtil = SpringUtil.getBean(MailUtil.class);
        File localFile = FileUtil.file(savePath);
        mailUtil.sendMail(this.email, "文件传递", StrUtil.EMPTY, localFile);
        FileUtil.del(savePath);
        log.info("\n, {} ===== file send to email end", LocalDateTime.now());
    }

}
