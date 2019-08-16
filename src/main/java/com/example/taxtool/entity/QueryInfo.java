package com.example.taxtool.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryInfo implements Serializable {
    String code;
    UserInfo[] data;
}
