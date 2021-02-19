package com.example.taxtool.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Max
 * @date 2021/2/18 17:19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ali-sms")
public class SmsConfig {

    private String accessKeyId;

    private String accessSecret;

    private String templateCode;

    private String signName;

    @Bean(name = "smsClient")
    public IAcsClient iAcsClient() {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        return new DefaultAcsClient(profile);
    }


}
