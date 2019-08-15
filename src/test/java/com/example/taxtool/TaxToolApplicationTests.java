package com.example.taxtool;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxToolApplicationTests {

    String cookie = "X-Authorization=eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJpdHMiLCJzdWIiOiIyMDAwMDAwMDAwOTczMDU4NjQiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE1NjU3OTU5MDAsImV4cCI6MTU2NTc5NzcwMCwienJyZGFoIjoiMTEwMDAwMDAwMjk5NjYzNjA0In0.SwRWo3ClbZ4kCdzkgLr_dft4w6e2-ga5EiYluL6pW9B5v_ayVQdS9pnJLRNDXgak0RoqKYQ36ZCPgG1my8DcKA;";


    @Test
    public void query() {
        HttpRequest request = HttpUtil.createGet("https://its.hljtax.gov.cn/web/zrr/sqbs/qybsryxx/query?dwdjxh=10214108000001267547");
        request.cookie(cookie);
        System.err.println(request.execute().body());
    }

    @Test
    public void add() {
        HttpRequest request = HttpUtil.createPost("https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/add?");

        String sfz = "";
        String xm = "";


        Map<String, String> params = new HashMap<>();
        params.put("dwdjxh", "10214108000001267547");
        params.put("bsqxlxDm", "10");
        params.put("bssqqq", LocalDate.now().toString());
        params.put("bssqqz", "");
        params.put("gjhdqszDm", "156");
        params.put("sfzjhm", sfz);
        params.put("xm", xm);

        System.err.println(request.body(new JSONObject(params)).execute().body());
    }

    @Test
    public void remove() {
        HttpRequest request = HttpUtil.createPost("https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/qy/remove?");

        UserInfo userInfo = new UserInfo();

        request.cookie(cookie);
        Map<String, String> params = new HashMap<>();
        params.put("dwdjxh",userInfo.getDwdjxh());
        params.put("bsqxlyDm", userInfo.getBsqxlyDm());
        params.put("bsqxlxDm", userInfo.getBsqxlxDm());
        params.put("bsryxh", userInfo.getBsryxh());
        params.put("gjhdqszDm", userInfo.getGjhdqszDm());
        params.put("sfzjhm", userInfo.getSfzjhm());
        params.put("sfzjlxDm", userInfo.getSfzjlxDm());
        params.put("xm", userInfo.getXm());
        params.put("zrrdah", userInfo.getZrrdah());

        System.err.println(request.body(new JSONObject(params)).execute().body());
    }

    @Test
    public void delete(){
        HttpRequest request = HttpUtil.createPost("https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/qy/delete?");
        request.cookie(cookie);

        UserInfo userInfo = new UserInfo();

        Map<String, String> params = new HashMap<>();
        params.put("dwdjxh", userInfo.getDwdjxh());
        params.put("bsryxh", userInfo.getBsryxh());
        params.put("zrrdah", userInfo.getZrrdah());
        System.err.println(request.body(new JSONObject(params)).execute().body());
    }


    /**
     *
     * X-Authorization=eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJpdHMiLCJzdWIiOiIyMDAwMDAwMDAwOTczMDU4NjQiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE1NjU3OTM4MDgsImV4cCI6MTU2NTc5NTYwOCwienJyZGFoIjoiMTEwMDAwMDAwMjk5NjYzNjA0In0.Nsrj9C6_FSH206b6i0fFkxAugP4VseFYd1Fm83Tx8TicphJXE1_8zWrWpjZ9RrgdoBR-tcwIwZJ7JBcWD8ABRQ; Oauth2zddlkey=eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJpdHMiLCJzdWIiOiIyMDAwMDAwMDAwOTczMDU4NjQiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE1NjU3OTM4MDgsImV4cCI6MTU2NTc5NTYwOCwienJyZGFoIjoiMTEwMDAwMDAwMjk5NjYzNjA0In0.Nsrj9C6_FSH206b6i0fFkxAugP4VseFYd1Fm83Tx8TicphJXE1_8zWrWpjZ9RrgdoBR-tcwIwZJ7JBcWD8ABRQ;
     */

    /**
     * 查询
     * https://its.hljtax.gov.cn/web/zrr/sqbs/qybsryxx/query?dwdjxh=10214108000001267547
     * get
     *
     */
    /**
     * 添加
     * https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/add?
     * POST
     * request:
     * {
     *     "dwdjxh":"10214108000001267547",
     *     "bsqxlxDm":"10",
     *     "bssqqq":"2019-08-14",
     *     "bssqqz":"",
     *     "gjhdqszDm":"156",
     *     "sfzjhm":"430621199505292746",
     *     "sfzjlxDm":"201",
     *     "xm":"聂峥勤"
     * }
     * response:
     * {
     *   "code" : "SUCCESS",
     *   "params" : null,
     *   "message" : null,
     *   "data" : null,
     *   "appCodeForEx" : null,
     *   "originalErrorCode" : null,
     *   "rid" : null
     * }
     */


    /**
     * https://its.hljtax.gov.cn/web/zrr/sqbs/qybsryxx/query?dwdjxh=10214108000001267547&_=1565793836529
     * GET
     * response:
     * {
     *   "code" : "SUCCESS",
     *   "params" : null,
     *   "message" : null,
     *   "data" : [ {
     *     "bsryxh" : "24250514",
     *     "zrrdah" : "110000000642570025",
     *     "dwdjxh" : "10114301000168549593",
     *     "qymc" : "湖南永雄资产管理集团有限公司",
     *     "sfzjhm" : "430621199505292746",
     *     "sfzjlxDm" : "201",
     *     "sfzjlxMc" : "居民身份证",
     *     "bsqxlxDm" : "10",
     *     "bsqxlxMc" : "办税权限",
     *     "bssqztDm" : "10",
     *     "bssqztMc" : "已授权",
     *     "bssqqq" : "2019-08-14",
     *     "bssqqz" : null,
     *     "sqrq" : "2019-08-14",
     *     "bsqxlyDm" : "20",
     *     "bsqxlyMc" : "ITS授权",
     *     "gjhdqszDm" : "156",
     *     "xm" : "聂峥勤"
     *   } ],
     *   "appCodeForEx" : null,
     *   "originalErrorCode" : null,
     *   "rid" : null
     * }
     */

    /**
     * 解除
     * https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/qy/remove?
     * post
     * request:
     * {
     *     "dwdjxh":"10214108000001267547",
     *     "bsqxlyDm":"20",
     *     "bsqxlxDm":"10",
     *     "bsryxh":"24250514",
     *     "gjhdqszDm":"156",
     *     "sfzjhm":"430621199505292746",
     *     "sfzjlxDm":"201",
     *     "xm":"聂峥勤",
     *     "zrrdah":"110000000642570025"
     * }
     * response:
     * {
     *   "code" : "SUCCESS",
     *   "params" : null,
     *   "message" : null,
     *   "data" : null,
     *   "appCodeForEx" : null,
     *   "originalErrorCode" : null,
     *   "rid" : null
     * }
     */

    /**
     * 删除
     * https://its.hljtax.gov.cn/web/zrr/sqbs/bsryxx/qy/delete?
     * post
     * request:
     * {
     * "dwdjxh":"10214108000001267547",
     * "bsryxh":"24250514",
     * "zrrdah":"110000000642570025"
     * }
     * response:
     * {
     * "code" : "SUCCESS",
     * "params" : null,
     * "message" : null,
     * "data" : null,
     * "appCodeForEx" : null,
     * "originalErrorCode" : null,
     * "rid" : null
     * }
     */


    public static Boolean isSuccess(String result) {
        return JSONUtil.toBean(result, Map.class).get("code").equals("SUCCESS");
    }
    @Test
    public void testsss(){
        String s =
                "   {\n" +
                        "        \"code\" : \"SUCCESS\",\n" +
                        "        \"params\" : null,\n" +
                        "        \"message\" : null,\n" +
                        "        \"data\" : [ {\n" +
                        "          \"bsryxh\" : \"24250514\",\n" +
                        "          \"zrrdah\" : \"110000000642570025\",\n" +
                        "          \"dwdjxh\" : \"10114301000168549593\",\n" +
                        "          \"qymc\" : \"湖南永雄资产管理集团有限公司\",\n" +
                        "          \"sfzjhm\" : \"430621199505292746\",\n" +
                        "          \"sfzjlxDm\" : \"201\",\n" +
                        "          \"sfzjlxMc\" : \"居民身份证\",\n" +
                        "          \"bsqxlxDm\" : \"10\",\n" +
                        "          \"bsqxlxMc\" : \"办税权限\",\n" +
                        "          \"bssqztDm\" : \"10\",\n" +
                        "          \"bssqztMc\" : \"已授权\",\n" +
                        "          \"bssqqq\" : \"2019-08-14\",\n" +
                        "          \"bssqqz\" : null,\n" +
                        "          \"sqrq\" : \"2019-08-14\",\n" +
                        "          \"bsqxlyDm\" : \"20\",\n" +
                        "          \"bsqxlyMc\" : \"ITS授权\",\n" +
                        "          \"gjhdqszDm\" : \"156\",\n" +
                        "          \"xm\" : \"聂峥勤\"\n" +
                        "        } ],\n" +
                        "        \"appCodeForEx\" : null,\n" +
                        "        \"originalErrorCode\" : null,\n" +
                        "        \"rid\" : null\n" +
                        "      }";


        QueryUsers queryUsers = JSONUtil.toBean(s, QueryUsers.class);

        System.err.println(queryUsers);
    }
}

@Data
class UserInfo {
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
     *
     */
    private String gjhdqszDm;
    /**
     * 姓名
     */
    private String xm;


}

