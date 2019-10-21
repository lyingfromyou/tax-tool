package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.utils.CommonConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
@RestController
public class FileController {

    private static final String UPLOAD_FILE_PATH = "/tmp/uploadFile/";


    @GetMapping(value = "/downloadFile", produces = "application/json;charset=utf-8")
    public String downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        try {
            List<String> fileList = FileUtil.listFileNames(CommonConstants.FILE_PATH);
            if (fileList.contains(fileName)) {
                ExcelReader reader = ExcelUtil.getReader(CommonConstants.FILE_PATH + fileName);
                ExcelWriter writer = reader.getWriter();
                //response为HttpServletResponse对象
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
                ServletOutputStream out = response.getOutputStream();

                // 一次性写出内容
                writer.flush(out, true);
                // 关闭writer，释放内存
                writer.close();
                //关闭输出Servlet流
                IoUtil.close(out);
            } else {
                return "没有这个文件";
            }
        } catch (IORuntimeException e) {
            System.err.println(e.getMessage());
            return "没有这个目录";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Ok";
    }

    @PostMapping(value = "/file/upload", produces = "application/json; charset=utf-8")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "没有文件";
        }

        String id = IdUtil.fastSimpleUUID();
        String fileName = file.getOriginalFilename();
        System.err.println(fileName);
        FileUtil.writeFromStream(file.getInputStream(), UPLOAD_FILE_PATH + id + StrUtil.SLASH + fileName);
        return  "129.28.131.210/file/download?fileId=" + id;
    }

    @GetMapping(value = "/file/download", produces = "application/json; charset=utf-8")
    public String fileDownload(@RequestParam String fileId, HttpServletResponse response) {
        List<File> localFiles = FileUtil.loopFiles(UPLOAD_FILE_PATH + fileId);
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

                boolean delete = FileUtil.del(UPLOAD_FILE_PATH + fileId);
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
