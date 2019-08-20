package com.example.taxtool.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.taxtool.entity.QueryInfo;
import com.example.taxtool.entity.UserInfo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxUtil {

    /**
     * 登记序号
     */
    private static final String dwdjxh = "10214108000001267547";



    public static List<UserInfo> queryList(String cookie) {
        HttpRequest request = HttpUtil.createGet("https://its.hljtax.gov.cn/web/zrr/sqbs/qybsryxx/query?dwdjxh=" + dwdjxh);
        request.cookie(cookie);
        String result = request.execute().body();
        if (isSuccess(result)) {
            QueryInfo queryInfo = JSONUtil.toBean(result, QueryInfo.class);
            return CollUtil.newArrayList(queryInfo.getData());
        }
        return null;
    }


    public static UserInfo query(String cookie, String xm) {
        HttpRequest request = HttpUtil.createGet("https://its.hljtax.gov.cn/web/zrr/sqbs/qybsryxx/query?dwdjxh=" + dwdjxh);
        request.cookie(cookie);
        String result = request.execute().body();
        if (isSuccess(result)) {
            QueryInfo queryInfo = JSONUtil.toBean(result, QueryInfo.class);
            for (UserInfo userInfo : queryInfo.getData()) {
                if (userInfo.getXm().equalsIgnoreCase(xm)) {
                    return userInfo;
                }
            }
        }
        return null;
    }


    public static Boolean create(String cookie, String xm, String sfz) {
        HttpRequest request = HttpUtil.createPost("https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/add?");
        request.cookie(cookie);
        Map<String, String> params = new HashMap<>();
        params.put("dwdjxh", dwdjxh);
        params.put("bsqxlxDm", "10");
        params.put("bssqqq", LocalDate.now().toString());
        params.put("bssqqz", null);
        params.put("gjhdqszDm", "156");
        params.put("sfzjlxDm", "201");
        params.put("sfzjhm", sfz);
        params.put("xm", xm);
        return isSuccess(request.body(new JSONObject(params)).execute().body());
    }

    public static Boolean remove(String cookie, UserInfo userInfo) {
        HttpRequest request = HttpUtil.createPost("https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/qy/remove?");

        request.cookie(cookie);
        Map<String, String> params = new HashMap<>();
        params.put("dwdjxh",dwdjxh);
        params.put("bsqxlyDm", userInfo.getBsqxlyDm());
        params.put("bsqxlxDm", userInfo.getBsqxlxDm());
        params.put("bsryxh", userInfo.getBsryxh());
        params.put("gjhdqszDm", userInfo.getGjhdqszDm());
        params.put("sfzjhm", userInfo.getSfzjhm());
        params.put("sfzjlxDm", userInfo.getSfzjlxDm());
        params.put("xm", userInfo.getXm());
        params.put("zrrdah", userInfo.getZrrdah());

        return isSuccess(request.body(new JSONObject(params)).execute().body());
    }

    public static Boolean delete(String cookie, UserInfo userInfo) {
        HttpRequest request = HttpUtil.createPost("https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/qy/delete?");
        request.cookie(cookie);

        Map<String, String> params = new HashMap<>();
        params.put("dwdjxh", dwdjxh);
        params.put("bsryxh", userInfo.getBsryxh());
        params.put("zrrdah", userInfo.getZrrdah());
        return isSuccess(request.body(new JSONObject(params)).execute().body());
    }

    public static Boolean isSuccess(String result) {
        return JSONUtil.toBean(result, Map.class).get("code").equals("SUCCESS");
    }


}
