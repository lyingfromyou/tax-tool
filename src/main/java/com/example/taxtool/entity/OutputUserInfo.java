package com.example.taxtool.entity;

import lombok.Data;

@Data
public class OutputUserInfo {

    private String xm;

    private String sfz;

    private String company;

    public OutputUserInfo(UserInfo userInfo) {
        this.xm =userInfo.getXm();
        this.sfz = userInfo.getSfzjhm();
        this.company = userInfo.getQymc();
    }

}
