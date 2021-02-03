package com.example.taxtool.otherController;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {


    public static void main(String[] args) {
        DateTime startTime = DateUtil.parseDateTime("2020-01-01 00:00:00");
        System.err.println(DateUtil.endOfMonth(DateUtil.offsetMonth(startTime, 6 - 1)));
    }

}
