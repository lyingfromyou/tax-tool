package com.example.taxtool;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author by Lying
 * @Date 2019/8/15
 */
public class Demo {

    private static final String dwdjxh = "10214108000001267547";

    public static void main(String[] args) {
        System.err.println("输入cookie: ");
        Scanner intput = new Scanner(System.in);

        String cookie = intput.next();

        System.err.println("输入姓名: ");
        String xm = intput.next();

        System.err.println("输入身份证: ");

        String sfz = intput.next();

        System.err.println("是否创建: ");

        String create = intput.next();

        if ("Y".equalsIgnoreCase(create)) {
            if (create(cookie, xm, sfz)) {
                UserInfo userInfo = query(cookie);
                if (null != userInfo) {
                    System.err.println("绑定的账号：");
                    System.err.println(JSONUtil.toJsonStr(userInfo));
                    System.err.println();
                    System.err.println("是否解除: ");
                    String remove = intput.next();
                    if ("Y".equalsIgnoreCase(remove)) {
                        if (remove(cookie, userInfo)) {
                            System.err.println("解除成功");
                            System.err.println();
                            System.err.println("是否删除: ");
                            String delete = intput.next();
                            if ("Y".equalsIgnoreCase(delete)) {
                                if (delete(cookie, userInfo)) {
                                    System.err.println("删除成功");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static UserInfo query(String cookie) {
        HttpRequest request = HttpUtil.createGet("https://its.hljtax.gov.cn/web/zrr/sqbs/qybsryxx/query?dwdjxh=" + dwdjxh);
        request.cookie(cookie);
        String result = request.execute().body();
        if (isSuccess(result)) {
            QueryUsers users = JSONUtil.toBean(result, QueryUsers.class);
            return users.data[0];
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

@Data
class QueryUsers implements Serializable {
    String code;
    UserInfo[] data;
}


@Data
class UserInfo implements Serializable {
    /**
     * 删除参数
     * 办税人员序号
     */
    private String bsryxh;
    /**
     * 删除参数
     */
    private String zrrdah;
    /**
     * 删除参数
     * 登记序号 10214108000001267547 固定
     */
    private String dwdjxh;
    /**
     * 公司
     */
    private String qymc;
    /**
     * 身份证件号码
     */
    private String sfzjhm;
    /**
     * 身份证件类型代码
     * "201"
     */
    private String sfzjlxDm;


    private String sfzjlxMc;
    /**
     * 办税权限类型代码
     * bsqxlxDm" : "10",
     */
    private String bsqxlxDm;
    private String bsqxlxMc;
    private String bssqztDm;
    private String bssqztMc;
    /**
     * 授权期限起
     * yyyy-mm-dd
     */
    private String bssqqq;
    /**
     * 授权期限止
     */
    private String bssqqz;
    private String sqrq;
    /**
     *
     */
    private String bsqxlyDm;
    private String bsqxlyMc;
    /**
     * 国家或地区数字代码 156 中国
     */
    private String gjhdqszDm;
    /**
     * 姓名
     */
    private String xm;


}
