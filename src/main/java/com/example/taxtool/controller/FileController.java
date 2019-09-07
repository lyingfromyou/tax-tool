package com.example.taxtool.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
@Controller
public class FileController {

    private static final String FILE_PATH = "/opt/files/";

    @GetMapping("/fileList")
    public String fileList(Map<String, Object> paramMap) {
        try {
            List<String> fileList = FileUtil.listFileNames(FILE_PATH);
            paramMap.put("fileList", fileList);
        } catch (IORuntimeException e) {
            System.err.println(e.getMessage());
        }
        return "fileList";
    }

    @ResponseBody
    @GetMapping(value = "/downloadFile", produces = "application/json;charset=utf-8")
    public String downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        try {
            List<String> fileList = FileUtil.listFileNames(FILE_PATH);
            if (fileList.contains(fileName)) {
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
                response.setCharacterEncoding("utf-8");
                byte[] buff = new byte[1024];
                BufferedInputStream bis = null;
                OutputStream os;
                try {
                    os = response.getOutputStream();
                    bis = new BufferedInputStream(new FileInputStream(FILE_PATH + fileName));
                    int i;
                    while ((i = bis.read(buff)) != -1) {
                        os.write(buff, 0, i);
                        os.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return "Ok";
            } else {
                return "没有这个文件";
            }
        } catch (IORuntimeException e) {
            System.err.println(e.getMessage());
            return "没有这个目录";
        }
    }


}
