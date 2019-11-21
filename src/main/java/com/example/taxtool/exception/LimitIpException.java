package com.example.taxtool.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by Lying
 * @Date 2019/11/21
 */
@Slf4j
public class LimitIpException extends RuntimeException {

    public LimitIpException(String ip, String url) {
      log.error("垃圾请求, ip: {} , rl: {}", ip, url);
    }
}
