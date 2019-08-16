package com.example.taxtool.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class InputUserInfo implements Serializable {

    /**
     * 姓名
     */
    String xm;

    /**
     * 身份证
     */
    String sfz;
}
