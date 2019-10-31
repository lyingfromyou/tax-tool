package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.taxtool.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author by Lying
 * @Date 2019/10/31
 */
@RestController
public class FileUploadAndDownloadController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping(value = "/file/upload", produces = "application/json; charset=utf-8")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "没有文件";
        }

        String beforeId = stringRedisTemplate.opsForValue().get(CommonConstants.UPLOAD_FILE_ID_KEY);
        String id;
        if (StrUtil.isBlank(beforeId)) {
            id = "1";
        } else {
            id = String.valueOf(Long.valueOf(beforeId) + 1);
        }
        stringRedisTemplate.opsForValue().set(CommonConstants.UPLOAD_FILE_ID_KEY, id);


        String fileName = file.getOriginalFilename();
        System.err.println(fileName);
        FileUtil.writeFromStream(file.getInputStream(), CommonConstants.FILE_UPLOAD_PATH + id + StrUtil.SLASH + fileName);
        return "129.28.131.210/file/download?fileId=" + id;
    }

    @GetMapping(value = "/file/download", produces = "application/json; charset=utf-8")
    public String fileDownload(@RequestParam String fileId, HttpServletResponse response) {
        List<File> localFiles = FileUtil.loopFiles(CommonConstants.FILE_UPLOAD_PATH + fileId);
        if (CollUtil.isNotEmpty(localFiles)) {
            File localFile = localFiles.get(0);
            downloadFile(fileId, localFile, response, true);
            return "ok";
        } else {
            return "没有这个文件, 检查你的路径";
        }
    }


    public static void downloadFile(String fileId, File file, HttpServletResponse response, boolean isDelete) {
        BufferedInputStream fis = null;
        OutputStream toClient = null;
        try {
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            // 清空response
            response.reset();
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
            byte[] buffer = new byte[1024 * 1024];
            int byteRead = 0;
            while ((byteRead = fis.read(buffer)) != -1) {
                toClient.write(buffer, 0, byteRead);
            }
            toClient.flush();
            if (isDelete) {

                boolean delete = FileUtil.del(CommonConstants.FILE_UPLOAD_PATH + fileId);
                System.err.println(delete);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (toClient != null) {
                    toClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
