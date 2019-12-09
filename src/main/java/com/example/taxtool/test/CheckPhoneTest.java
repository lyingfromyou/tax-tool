package com.example.taxtool.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.taxtool.entity.CheckPhoneResultEntity;
import com.example.taxtool.task.GetCheckPhoneResultTask;
import com.example.taxtool.utils.CommonConstants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by Lying
 * @Date 2019/12/9
 */
public class CheckPhoneTest {


    @Test
    public void test1() {
        File file = new File("C:\\Users\\Lying\\Desktop\\check.txt");
        String resultStr =
                HttpUtil.post(CommonConstants.CHECK_PHONE_UPLOAD_URL, getUploadRequestMap(file));
        System.err.println(resultStr);
    }
//    {"RES":"100","ERR":"","DATA":{"sendID":"42992"}}

    @Test
    public void test2() {
        String resultStr = HttpUtil.post(CommonConstants.CHECK_PHONE_GET_RESULT_URL, getResultRequestMap("42992"));
        System.err.println(resultStr);
    }
//{"RES":"100","ERR":"","DATA":{"status":"2","number2":"269","number3":"60","number4":"47","number5":"130"}}


    @Test
    public void test3() throws IOException {
        String url = "https://xcjk.mobwin.me/api/Download.ashx";
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("account", "16670409213");
        requestMap.put("pass", "Sanpang1112");
        requestMap.put("sendID", "42992");


        // 1代表下载压缩包，2代表活跃号文件，3代表空号文件，4代表沉默号文件，5代表风险号
        requestMap.put("type", "1");

        HttpResponse response = com.example.taxtool.utils.HttpUtil.download(url, requestMap);
        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();
        File zipFile = FileUtil.writeFromStream(inputStream, "D:\\ss.zip");
        File unzipDir = ZipUtil.unzip(zipFile, Charset.forName("GBK"));

        List<File> listFile = FileUtil.loopFiles(unzipDir);
        for (File resultFile : listFile) {
            List<String> strList = FileUtil.readLines(resultFile, Charset.defaultCharset());


            System.err.println();
            System.err.println(StrUtil.removeSuffix(resultFile.getName(), ".txt"));
            System.err.println(strList);

        }
    }


    @Test
    public void test4() {
        String path = "C:\\Users\\Lying\\Desktop\\主单短信整合(1).xlsx";
        ExcelReader excelReader = ExcelUtil.getReader(path);

        Map<String, String> headerAlias = new HashMap<>();
        headerAlias.put("客户姓名", "name");
        headerAlias.put("手机号码", "phone");
        headerAlias.put("备注", "remark");

        excelReader.setHeaderAlias(headerAlias);


        Map<String, CheckPhoneResultEntity> totalMap = new HashMap<>();
        for (int i = 0; i < excelReader.getSheetCount(); i++) {
            excelReader.setSheet(i);
            List<CheckPhoneResultEntity> list = excelReader.readAll(CheckPhoneResultEntity.class);
            if (CollUtil.isNotEmpty(list)) {
                list.stream().forEach(l -> totalMap.put(l.getPhone(), l));
            }
        }

        System.err.println(totalMap.size());

    }


    @Test
    public void test5() throws InterruptedException {

        Thread t = new Thread(new GetCheckPhoneResultTask("43151", "183023840@qq.com", null, null));

        t.start();
        t.join();

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
