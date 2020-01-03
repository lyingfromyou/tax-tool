package com.example.taxtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.example.taxtool.filter")
public class TaxToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxToolApplication.class, args);
    }

}
