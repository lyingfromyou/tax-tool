package com.example.taxtool.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author by Lying
 * @Date 2019/12/5
 */
@Data
public class CheckPhoneResult implements Serializable {


    private String RES;
    private String ERR;

    private CheckPhoneResultData DATA;

    @Data
    public class CheckPhoneResultData {
        private String status;
        private String number2;
        private String number3;
        private String number4;
        private String number5;
    }
}
