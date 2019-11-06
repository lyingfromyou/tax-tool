package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.entity.CommonUserInfo;
import com.example.taxtool.entity.MergeUserInfo;
import com.example.taxtool.entity.OutputUserInfo;
import com.example.taxtool.entity.UserPhone;
import com.example.taxtool.utils.CommonConstants;
import com.example.taxtool.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
@RestController
public class MergeFileController {

    @Autowired
    private MailUtil mailUtil;

    @PostMapping(value = "/mergeFile", produces = "application/json; charset=utf-8")
    public String mergeExcel(HttpServletRequest request, @RequestParam String fileName,
                             @RequestParam String email, @RequestParam String mergeKey) {
        long start = System.currentTimeMillis();
        //获取上传的文件数组
        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("fileList");
        if (CollUtil.isEmpty(fileList) && fileList.size() > 1) {
            return "文件列表不能为空且必须要两个文件及以上";
        }

        List<File> localFileList = new ArrayList<>();
        for (MultipartFile multipartFile : fileList) {
            try {
                File localFile = FileUtil.writeFromStream(
                        multipartFile.getInputStream(),
                        CommonConstants.MERGE_FILE_UPLOAD_PATH + multipartFile.getOriginalFilename());
                localFileList.add(localFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String, Map<String, String>> data = mergeFile(localFileList, mergeKey);
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(data.values());

        String savePath = CommonConstants.MERGE_FILE_PATH + fileName;
        writer.flush(FileUtil.newFile(savePath));
        writer.close();
        localFileList.stream().forEach(file -> FileUtil.del(file.getAbsolutePath()));

        String content = String.format("合并 %s 个文件, 一共用时: %s 毫秒. ", fileList.size(),
                System.currentTimeMillis() - start);
        System.err.println(content);
        new Thread(() -> {
            File mergeFile = FileUtil.file(savePath);
            mailUtil.sendMail(email, "合并文件结果", content, mergeFile);
            FileUtil.del(mergeFile.getAbsolutePath());
        }).start();
        return "合并完成, 请注意查收";
    }


//    @PostMapping(value = "/mergeFile", produces = "application/json; charset=utf-8")
//    public String mergeExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) throws IOException {
//        long start = System.currentTimeMillis();
//        //获取上传的文件数组
//        List<MultipartFile> phoneFileList = ((MultipartHttpServletRequest) request).getFiles("phoneFileList");
//        List<MultipartFile> taxFileList = ((MultipartHttpServletRequest) request).getFiles("taxFileList");
//        if (CollUtil.isEmpty(phoneFileList)) {
//            return "电话信息文件不能为空";
//        }
//        if (CollUtil.isEmpty(taxFileList)) {
//            return "公司信息不能为空";
//        }
//
//        List<CommonUserInfo> unMatchedUser = new ArrayList<>();
//
//        Set<MergeUserInfo> mergeUserInfos = handle(phoneFileList, taxFileList, unMatchedUser);
//        if (CollUtil.isNotEmpty(mergeUserInfos)) {
//            ExcelWriter writer = new ExcelWriter(true, "合并信息");
//            writer.addHeaderAlias("phone", "电话");
//            writer.addHeaderAlias("name", "姓名");
//            writer.addHeaderAlias("company", "公司");
//            // 一次性写出内容，使用默认样式，强制输出标题
//            writer.write(CollUtil.sort(mergeUserInfos, Comparator.comparing(info -> info.getName())), true);
//
//            if (CollUtil.isNotEmpty(unMatchedUser)) {
//                writer.setSheet("未合并信息");
//                writer.addHeaderAlias("phone", "电话");
//                writer.addHeaderAlias("xm", "姓名");
//                writer.addHeaderAlias("company", "公司");
//                writer.addHeaderAlias("sfz", "身份证");
//
//                // 一次性写出内容，使用默认样式，强制输出标题
//                writer.write(unMatchedUser, true);
//
//            }
//
//            fileName = StrUtil.addSuffixIfNot(fileName, ".xlsx");
//            //response为HttpServletResponse对象
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
//            //out为OutputStream，需要写出到的目标流
//            ServletOutputStream out = response.getOutputStream();
//
//            writer.flush(out, true);
//            // 关闭writer，释放内存
//            writer.close();
//            //此处记得关闭输出Servlet流
//            IoUtil.close(out);
//        }
//        System.err.println("一共用时: " + (System.currentTimeMillis() - start) + " 毫秒");
//        return "ok";
//    }


    private Set<MergeUserInfo> handle(List<MultipartFile> phoneFileList, List<MultipartFile> taxFileList,
                                      List<CommonUserInfo> unMatchedUser) {
        Set<MergeUserInfo> mergeUserInfos = new HashSet<>();
        List<UserPhone> totalUserPhoneList = new ArrayList<>();
        List<OutputUserInfo> totalUserCompanyList = new ArrayList<>();
        for (MultipartFile file : phoneFileList) {
            String originalFilename = file.getOriginalFilename();
            String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            try {
                System.err.println("读取电话信息文件: " + fileName);
                ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
                reader.addHeaderAlias("电话", "phone");
                reader.addHeaderAlias("姓名", "xm");
                totalUserPhoneList.addAll(reader.readAll(UserPhone.class).stream()
                        .filter(userPhone -> StrUtil.isNotBlank(userPhone.getPhone()))
                        .collect(Collectors.toList()));
            } catch (IOException e) {
                System.err.println("读取" + fileName + "文件失败: " + e.getMessage());
            }
        }

        String userName = "";
        for (UserPhone userPhone : totalUserPhoneList) {
            if (StrUtil.isNotBlank(userPhone.getXm())) {
                userName = userPhone.getXm();
            } else {
                userPhone.setXm(userName);
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
                totalUserCompanyList.addAll(reader.readAll(OutputUserInfo.class).stream()
                        .filter(outputUserInfo ->
                                StrUtil.isNotBlank(outputUserInfo.getXm()) &&
                                        StrUtil.isNotBlank(outputUserInfo.getCompany()))
                        .collect(Collectors.toList()));
            } catch (IOException e) {
                System.err.println("读取" + fileName + "文件失败: " + e.getMessage());
            }
        }

        for (UserPhone userPhone : totalUserPhoneList) {
            String xm = userPhone.getXm();
            List<OutputUserInfo> singleUserInfoList = totalUserCompanyList.stream()
                    .filter(info -> (xm.equalsIgnoreCase(info.getXm()) && StrUtil.isNotBlank(info.getCompany())))
                    .collect(Collectors.toList());

            if (CollUtil.isNotEmpty(singleUserInfoList)) {
                singleUserInfoList.stream().forEach(user ->
                        mergeUserInfos.add(new MergeUserInfo(userPhone.getPhone(), xm, user.getCompany()))
                );
            }
        }

        for (UserPhone userPhone : totalUserPhoneList) {
            boolean flag = false;
            for (OutputUserInfo outputUserInfo : totalUserCompanyList) {
                if (outputUserInfo.getXm().equals(userPhone.getXm())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                unMatchedUser.add(new CommonUserInfo(userPhone));
            }
        }

        for (OutputUserInfo outputUserInfo : totalUserCompanyList) {
            boolean flag = false;
            for (UserPhone userPhone : totalUserPhoneList) {
                if (outputUserInfo.getXm().equals(userPhone.getXm())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                unMatchedUser.add(new CommonUserInfo(outputUserInfo));
            }
        }
        return mergeUserInfos;
    }


    public static void downloadFile(File file, HttpServletResponse response, boolean isDelete) {
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
                file.delete(); // 是否将生成的服务器端文件删除
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

    public static Map<String, Map<String, String>> mergeFile(List<File> fileList, String key) {
        Map<String, Map<String, String>> allData = new HashMap<>();
        for (File file : fileList) {
            ExcelReader reader = ExcelUtil.getReader(file);
            List<Map<String, Object>> list = reader.readAll();
            for (Map<String, Object> map : list) {
                if (map.containsKey(key)) {
                    String keyVal = map.get(key).toString();
                    Map<String, String> data;
                    if (allData.containsKey(keyVal)) {
                        data = allData.get(keyVal);
                    } else {
                        data = new HashMap<>();
                        allData.put(keyVal, data);
                    }

                    map.entrySet().stream().forEach(entry -> {
                        String entryKey = entry.getKey();
                        String entryVal = entry.getValue() != null ? entry.getValue().toString() : "";
                        if (data.containsKey(entryKey) && !key.equals(entryKey)) {
                            String oldVal = data.get(entryKey);
                            if (StrUtil.isNotBlank(oldVal)) {
                                entryVal = String.join(" ,", oldVal, entryVal);
                            }
                        }
                        data.put(entryKey, entryVal);
                    });
                }
            }
        }
        return allData;
    }


}
