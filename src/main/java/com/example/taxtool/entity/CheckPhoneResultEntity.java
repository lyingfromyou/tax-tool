package com.example.taxtool.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author by Lying
 * @Date 2019/12/9
 */
@Data
public class CheckPhoneResultEntity implements Serializable {


    private String name;

    private String phone;

    private String remark;


}
