package com.example.taxtool;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by Lying
 * @Date 2019/10/23
 */
public class DemoTest {

    @Test
    public void test1(){
        String s = "姓名: {姓名}, 年龄: {年龄}";
        Map<String, String> map = new HashMap<>();
        map.put("姓名", "lq");
        map.put("年龄", "365");
        map.put("123", "fdg");
        System.err.println(StrUtil.format(s, map));

    }

    @Test
    public void test2(){
        for (Annotation annotation : AnnotationTest.class.getAnnotations()){
            System.err.println(annotation);
        }

    }
}
