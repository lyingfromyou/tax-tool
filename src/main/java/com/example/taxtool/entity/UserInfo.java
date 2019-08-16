package com.example.taxtool.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

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
