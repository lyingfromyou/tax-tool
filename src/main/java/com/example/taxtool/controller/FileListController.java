package com.example.taxtool.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.utils.CommonConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author by Lying
 * @Date 2019/10/31
 */
@RestController
public class FileListController {


    @GetMapping(value = "/downloadCompanyInfoFile", produces = "application/json;charset=utf-8")
    public String downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        try {
            List<String> fileList = FileUtil.listFileNames(CommonConstants.COMPANY_INFO_FILE_PATH);
            if (fileList.contains(fileName)) {
                ExcelReader reader = ExcelUtil.getReader(CommonConstants.COMPANY_INFO_FILE_PATH + fileName);
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

}
