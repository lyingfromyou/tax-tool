package com.example.taxtool.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author by Lying
 * @Date 2019/12/5
 */
@Data
public class CheckPhoneUploadResult implements Serializable {

    private String RES;
    private String ERR;
    private UploadResultData DATA;


    @Data
    public class UploadResultData implements Serializable {
        private String sendID;
    }
}
