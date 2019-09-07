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

    @GetMapping("/mergeView")
    public String mergeView(){
        return "mergeView";
    }

    @GetMapping("/fileList")
    public String fileList(Map<String, Object> paramMap) {
        try {
            List<String> fileList = FileUtil.listFileNames(CommonConstants.FILE_PATH);
            paramMap.put("fileList", fileList);
        } catch (IORuntimeException e) {
            System.err.println(e.getMessage());
        }
        return "fileList";
    }

}
