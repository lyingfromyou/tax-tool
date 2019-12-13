package com.example.taxtool;

import cn.hutool.core.util.RandomUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@SpringBootApplication
@ServletComponentScan("com.example.taxtool.filter")
public class TaxToolApplication {

    public static final Set<String> randomUrl = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {

        IntStream.range(0, 1000).forEach(i ->randomUrl.add("/" +  RandomUtil.randomInt(0, Integer.MAX_VALUE)));

       ;



        SpringApplication.run(TaxToolApplication.class, args);
    }

}
