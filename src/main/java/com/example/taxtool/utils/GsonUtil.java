package com.example.taxtool.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;

/**
 * @author by Lying
 * @Date 2019/10/23
 */
@UtilityClass
public final class GsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /**
     * 输出打印
     *
     * @param o
     */
    public void dump(Object o) {
        System.err.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(gson.toJson(o));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.err.println();
    }

    /**
     * To Json
     *
     * @param o
     * @return
     */
    public String toJson(Object o) {
        return gson.toJson(o);
    }

    /**
     * Json字符串转化为对象
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public <T> T fromJson(Object obj, Class<T> classOfT) {
        return gson.fromJson(toJson(obj), classOfT);
    }
}