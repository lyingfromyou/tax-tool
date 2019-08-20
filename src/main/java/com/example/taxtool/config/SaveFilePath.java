package com.example.taxtool.config;


import cn.hutool.core.util.StrUtil;

public class SaveFilePath {


    public static String FILE_PATH;

    static {
        String path = System.getenv("TAX_PATH");
        if (StrUtil.isNotBlank(path)) {
            SaveFilePath.FILE_PATH = path;
        } else {
            SaveFilePath.FILE_PATH = "/opt/files/";
        }
    }

}
