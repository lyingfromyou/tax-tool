package com.example.taxtool.service;

import com.aliyuncs.IAcsClient;
import com.example.taxtool.config.SmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Max
 * @date 2021/2/18 18:32
 */
@Service
public class SmsService {

    @Autowired
    private SmsConfig smsConfig;
    @Autowired
    @Qualifier("smsClient")
    private IAcsClient smsClient;

    public void send() {



    }

}
