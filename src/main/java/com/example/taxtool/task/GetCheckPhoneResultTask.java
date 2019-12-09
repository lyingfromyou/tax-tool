package com.example.taxtool.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.taxtool.controller.CheckPhoneController;
import com.example.taxtool.entity.CheckPhoneResult;
import com.example.taxtool.entity.CheckPhoneResultEntity;
import com.example.taxtool.utils.CommonConstants;
import com.example.taxtool.utils.GsonUtil;
import com.example.taxtool.utils.MailUtil;
import org.apache.http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/12/5
 */

public class GetCheckPhoneResultTask implements Runnable {

    private String sendId;
    private String email;
    private ExcelWriter writer;
    private Map<String, CheckPhoneResultEntity> totalMap;
    private MailUtil mailUtil;


    private static String ZIP_FILE_PATH = "/opt/files/zip_file/";
    private static String SAVE_HANDLE_FILE_PATH = "/opt/files/save_handle_file/";


    public GetCheckPhoneResultTask(String sendId, String email, Map<String, CheckPhoneResultEntity> totalMap, MailUtil mailUtil) {
        this.sendId = sendId;
        this.totalMap = totalMap;
        this.mailUtil = mailUtil;
        this.email = email;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(15);
                String resultStr = HttpUtil.post(CommonConstants.CHECK_PHONE_GET_RESULT_URL, CheckPhoneController.getResultRequestMap(sendId));
                if (StrUtil.isNotBlank(resultStr)) {
                    CheckPhoneResult checkPhoneResult = GsonUtil.fromJson(resultStr, CheckPhoneResult.class);
                    if (null != checkPhoneResult && checkPhoneResult.getRES().equals("100")) {
                        HttpResponse response = com.example.taxtool.utils.HttpUtil
                                .download(CommonConstants.CHECK_PHONE_DOWNLOAD_URL, getRequestMap());
                        InputStream inputStream = response.getEntity().getContent();
                        File zipFile = FileUtil.writeFromStream(inputStream, ZIP_FILE_PATH + this.sendId + ".zip");
                        File unzipDir = ZipUtil.unzip(zipFile, Charset.forName("GBK"));
                        List<File> listFile = FileUtil.loopFiles(unzipDir);
                        for (File resultFile : listFile) {
                            List<String> strList = FileUtil.readLines(resultFile, Charset.defaultCharset());
                            String sheetName = StrUtil.removeSuffix(resultFile.getName(), ".txt");
                            writeData(sheetName, strList);
                        }

                        String path = SAVE_HANDLE_FILE_PATH + this.sendId + ".xlsx";
                        writer.flush(FileUtil.touch(path));
                        writer.close();
                        new Thread(() -> {
                            File sendFile = FileUtil.file(path);
                            mailUtil.sendMail(email, "处理结果", StrUtil.EMPTY, sendFile);
                            FileUtil.del(sendFile.getAbsolutePath());
                        }).start();
                        return;
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeData(String sheetName, List<String> strList) {
        if (CollUtil.isNotEmpty(strList)) {
            if (null == this.writer) {
                this.writer = new ExcelWriter(true, sheetName);
                Map<String, String> headerAlias = new HashMap<>();
                headerAlias.put("name", "客户姓名");
                headerAlias.put("phone", "手机号码");
                headerAlias.put("remark", "备注");
                writer.setHeaderAlias(headerAlias);
            } else {
                this.writer.setSheet(sheetName);
            }

            Set<CheckPhoneResultEntity> entitySet = totalMap.keySet().stream()
                    .filter(strList::contains).map(totalMap::get).collect(Collectors.toSet());
            writer.write(entitySet);
        }
    }

    private Map<String, String> getRequestMap() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("account", "16670409213");
        requestMap.put("pass", "Sanpang1112");
        requestMap.put("sendID", this.sendId);
        // 1代表下载压缩包，2代表活跃号文件，3代表空号文件，4代表沉默号文件，5代表风险号
        requestMap.put("type", "1");
        return requestMap;
    }

}
