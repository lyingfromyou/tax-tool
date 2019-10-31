package com.example.taxtool.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.example.taxtool.utils.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author by Lying
 * @Date 2019/9/7
 */
@Controller
public class ViewController {

    @GetMapping("/sendMailPage")
    public String sendMailPage(){
        return "sendMailPage";
    }

    @GetMapping("/userCompanyInfoPage")
    public String userCompanyInfoPage(){
        return "userCompanyInfoPage";
    }

    @GetMapping("/mergeFilePage")
    public String mergeFilePage(){
        return "mergeFilePage";
    }

    @GetMapping("/fileListPage")
    public String fileListPage(Map<String, Object> paramMap) {
        try {
            List<String> fileList = FileUtil.listFileNames(CommonConstants.COMPANY_INFO_FILE_PATH);
            paramMap.put("fileList", fileList);
        } catch (IORuntimeException e) {
            System.err.println(e.getMessage());
        }
        return "fileListPage";
    }


    @GetMapping("/fileUploadAndDownloadPage")
    public String fileUploadAndDownloadPage(){
        return "fileUploadAndDownloadPage";
    }


}
