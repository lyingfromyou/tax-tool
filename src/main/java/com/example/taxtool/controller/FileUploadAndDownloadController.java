package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.taxtool.entity.UploadSession;
import com.example.taxtool.task.FileSendToEmailTask;
import com.example.taxtool.utils.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author by Lying
 * @Date 2019/10/31
 */
@Slf4j
@RestController
public class FileUploadAndDownloadController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ThreadPoolExecutor executor;

    private static final String DOWNLOAD_PREFIX = "/files";
    private static String filePath = CommonConstants.BASE_PATH + CommonConstants.FILE_UPLOAD_PATH;

    public static final Map<UploadSession, StringBuffer> contentMap = Collections.synchronizedMap(new HashMap<>());

    @GetMapping(value = "/file/upload", produces = "application/json; charset=utf-8")
    public String textUpload(@RequestHeader String uuid,
                             @RequestHeader(required = false) String endSignal,
                             @RequestParam String email, @RequestParam String text) {
        text = StrUtil.trim(text);
        if (StrUtil.isBlank(text) && StrUtil.isBlank(endSignal)) {
            return "OK";
        }

        UploadSession session = UploadSession.builder().uuid(uuid).email(email).build();
        StringBuffer buffer = contentMap.get(session);
        if (null == buffer) {
            buffer = new StringBuffer();
        }

        appendContent(text, buffer);

        if (StrUtil.isNotBlank(endSignal)) {
            buffer = contentMap.remove(session);
            log.info("=====================upload finish====================");
            executor.execute(new FileSendToEmailTask(email, buffer));
        } else {
            log.info("upload test success: " + text);
            contentMap.put(session, buffer);
        }
        return "OK";
    }

    private synchronized void appendContent(String text, StringBuffer buffer) {
        if (StrUtil.isNotBlank(text)) {
            buffer.append(text);
            buffer.append(StrUtil.CRLF);
        }
    }

    @PostMapping(value = "/file/upload", produces = "application/json; charset=utf-8")
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
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

        FileUtil.writeFromStream(file.getInputStream(), filePath + id + StrUtil.SLASH + fileName);

        String rootUrl = request.getRequestURL().toString().replace(request.getRequestURI(), StrUtil.EMPTY);

        String resultUrl = "";
        if (rootUrl.contains("localhost")) {
            resultUrl = rootUrl + DOWNLOAD_PREFIX + "/"
                    + CommonConstants.FILE_UPLOAD_PATH + id + StrUtil.SLASH + fileName;
        } else {
            rootUrl = rootUrl.replace(":8899", StrUtil.EMPTY).replace("http://", StrUtil.EMPTY)
                    .replace("https://", StrUtil.EMPTY);

            InetAddress ip = InetAddress.getByName(rootUrl);
            System.out.println("IP地址:" + ip.getHostAddress());
            System.out.println("域名：" + ip.getHostName());
            resultUrl = ip.getHostAddress() + DOWNLOAD_PREFIX + "/"
                    + CommonConstants.FILE_UPLOAD_PATH + id + StrUtil.SLASH + fileName;
        }
        System.out.println("resultUrl: " + resultUrl);
        return resultUrl;
    }

    @GetMapping(value = "/file/download", produces = "application/json; charset=utf-8")
    public String fileDownload(@RequestParam String fileId, HttpServletResponse response) {
        List<File> localFiles = FileUtil.loopFiles(filePath + fileId);
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
                boolean delete = FileUtil.del(filePath + fileId);
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
