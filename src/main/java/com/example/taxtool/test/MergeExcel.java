package com.example.taxtool.test;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by Lying
 * @Date 2019/8/17
 */
public class MergeExcel {

    private final static String BASE_PATH = "d:\\";


    public static void main(String[] args) {
//        List<File> files = listFile("d:\\");
//        if (CollUtil.isNotEmpty(files)) {
//            for (File file : files) {
//                ExcelWriter writer = ExcelUtil.getWriter(
//                        BASE_PATH + LocalDate.now() + StrUtil.DASHED + "tax.xlsx");
//                ExcelReader reader = ExcelUtil.getReader(file);
//                System.err.println(  reader.readAll());
////                reader.addHeaderAlias("姓名", "xm");
////                reader.addHeaderAlias("身份证", "sfz");
////                reader.addHeaderAlias("公司", "company");
////                List<OutputUserInfo> infos = reader.readAll(OutputUserInfo.class);
////
////                System.err.println(infos);
//            }
//        }

        File file = new File(BASE_PATH + "2019-08-17-1-tax.xlsx");
        ExcelReader reader = ExcelUtil.getReader(file);
        System.err.println(reader.readAll());
    }


    public static List<File> listFile(String path) {
        List<String> fileNames = FileUtil.listFileNames(path);
        if (CollUtil.isNotEmpty(fileNames)) {
            return fileNames.stream()
                    .filter(fileName -> fileName.contains(".xlsx"))
                    .map(fileName -> new File(path + fileName))
                    .collect(Collectors.toList());
        }
        return null;
    }

}
