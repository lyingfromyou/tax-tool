package com.example.taxtool.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.taxtool.entity.CheckPhoneResultEntity;
import com.example.taxtool.task.GetCheckPhoneResultTask;
import com.example.taxtool.utils.CommonConstants;
import com.example.taxtool.utils.GsonUtil;
import com.example.taxtool.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/12/9
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private ThreadPoolExecutor executor;

    @PostMapping(value = "/checkPhone", produces = "application/json;charset=utf-8")
    public String checkPhone(@RequestParam("file") MultipartFile file, @RequestParam String email) throws IOException {
        File localFile = FileUtil.writeFromStream(
                file.getInputStream(),
                CommonConstants.CHECK_PHONE_UPLOAD_FILE_PATH + file.getOriginalFilename());

        ExcelReader excelReader = ExcelUtil.getReader(localFile);
        Map<String, String> headerAlias = new HashMap<>();
        headerAlias.put("客户姓名", "name");
        headerAlias.put("手机号码", "phone");
        headerAlias.put("备注", "remark");
        excelReader.setHeaderAlias(headerAlias);

        List<CheckPhoneResultEntity> totalEntity = new ArrayList<>();
        for (int i = 0; i < excelReader.getSheetCount(); i++) {
            excelReader.setSheet(i);
            List<CheckPhoneResultEntity> entities = excelReader.readAll(CheckPhoneResultEntity.class);
            if (CollUtil.isNotEmpty(entities)) {
                totalEntity.addAll(entities);
            }
        }

        if (CollUtil.isNotEmpty(totalEntity) && totalEntity.size() > 500) {
            Map<String, CheckPhoneResultEntity> totalMap =
                    totalEntity.stream().collect(Collectors.toMap(
                            CheckPhoneResultEntity::getPhone,
                            (entity) -> entity,
                            (key1, key2) -> key2
                    ));

            Set<String> phoneSet = totalMap.keySet();
            GsonUtil.dump(phoneSet);

            if (true) {
                String sendId = "43175";
//                String sendId = "43178";
                executor.execute(new GetCheckPhoneResultTask(sendId, email, totalMap, mailUtil));
                delFiles(CommonConstants.CHECK_PHONE_UPLOAD_FILE_PATH);
                return "上传成功, sendId: " + sendId;
            } else {
                delFiles(CommonConstants.CHECK_PHONE_UPLOAD_FILE_PATH);
                return "处理失败";
            }
        } else {
            delFiles(CommonConstants.CHECK_PHONE_UPLOAD_FILE_PATH);
            return "数据必须大于500";
        }
    }


    private void delFiles(String path) {
        FileUtil.del(path);
    }

    public static final Map<String, Object> getUploadRequestMap(File file) {
        Map<String, Object> requestMap = getBaseRequestMap();
        requestMap.put("file", file);
        return requestMap;
    }

    public static final Map<String, Object> getResultRequestMap(String sendID) {
        Map<String, Object> requestMap = getBaseRequestMap();
        requestMap.put("sendID", sendID);
        return requestMap;
    }

    public static final Map<String, Object> getBaseRequestMap() {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("account", "16670409213");
        requestMap.put("pass", "Sanpang1112");
        return requestMap;
    }

}
