package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.entity.MergeUserInfo;
import com.example.taxtool.entity.OutputUserInfo;
import com.example.taxtool.entity.UserPhone;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
@RestController
public class MergeExcelController {

    @PostMapping(value = "/mergeExcel", produces = "application/json; charset=utf-8")
    public String mergeExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) throws IOException {
        long start = System.currentTimeMillis();
        //获取上传的文件数组
        List<MultipartFile> phoneFileList = ((MultipartHttpServletRequest) request).getFiles("phoneFileList");
        List<MultipartFile> taxFileList = ((MultipartHttpServletRequest) request).getFiles("taxFileList");
        if (CollUtil.isEmpty(phoneFileList)) {
            return "电话信息文件不能为空";
        }
        if (CollUtil.isEmpty(taxFileList)) {
            return "公司信息不能为空";
        }

        Set<MergeUserInfo> mergeUserInfos = handle(phoneFileList, taxFileList);
        if (CollUtil.isNotEmpty(mergeUserInfos)) {
            ExcelWriter writer = ExcelUtil.getWriter(true);
            writer.addHeaderAlias("phone", "电话");
            writer.addHeaderAlias("name", "姓名");
            writer.addHeaderAlias("company", "公司");

            fileName = StrUtil.addSuffixIfNot(fileName, ".xlsx");

            // 一次性写出内容，使用默认样式，强制输出标题
            writer.write(mergeUserInfos, true);
            //out为OutputStream，需要写出到的目标流

            //response为HttpServletResponse对象
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            ServletOutputStream out = response.getOutputStream();

            writer.flush(out, true);
            // 关闭writer，释放内存
            writer.close();
            //此处记得关闭输出Servlet流
            IoUtil.close(out);
        }
        System.err.println("一共用时: " + ((System.currentTimeMillis() - start) / 1000) + " 秒");
        return "ok";
    }


    private Set<MergeUserInfo> handle(List<MultipartFile> phoneFileList, List<MultipartFile> taxFileList) {
        Set<MergeUserInfo> mergeUserInfos = new HashSet<>();
        Set<UserPhone> totalUserPhoneSet = new HashSet<>();
        Set<OutputUserInfo> totalUserCompanySet = new HashSet<>();
        for (MultipartFile file : phoneFileList) {
            String originalFilename = file.getOriginalFilename();
            String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            try {
                System.err.println("读取电话信息文件: " + fileName);
                ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
                reader.addHeaderAlias("电话", "phone");
                reader.addHeaderAlias("姓名", "xm");
                totalUserPhoneSet.addAll(reader.readAll(UserPhone.class));
            } catch (IOException e) {
                System.err.println("读取" + fileName + "文件失败: " + e.getMessage());
            }
        }

        for (MultipartFile file : taxFileList) {
            String originalFilename = file.getOriginalFilename();
            String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            try {
                System.err.println("读取公司信息文件: " + fileName);
                ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
                reader.addHeaderAlias("姓名", "xm");
                reader.addHeaderAlias("身份证", "sfz");
                reader.addHeaderAlias("公司", "company");
                totalUserCompanySet.addAll(reader.readAll(OutputUserInfo.class));
            } catch (IOException e) {
                System.err.println("读取" + fileName + "文件失败: " + e.getMessage());
            }
        }

        for (UserPhone userPhone : totalUserPhoneSet) {
            String xm = userPhone.getXm();
            Set<OutputUserInfo> singleUserInfoSet = totalUserCompanySet.stream()
                    .filter(info -> (xm.equalsIgnoreCase(info.getXm()) && StrUtil.isNotBlank(info.getCompany())))
                    .collect(Collectors.toSet());

            if (CollUtil.isNotEmpty(singleUserInfoSet)) {
                singleUserInfoSet.stream().forEach(user ->
                        mergeUserInfos.add(new MergeUserInfo(userPhone.getPhone(), xm, user.getCompany()))
                );
            }
        }
        return mergeUserInfos;
    }

}
