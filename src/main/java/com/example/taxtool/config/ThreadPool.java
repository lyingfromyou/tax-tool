package com.example.taxtool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPool {


    @Bean
    public ThreadPoolExecutor executor(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 80, 1, TimeUnit.MINUTES, blockingQueue);
        return threadPoolExecutor;
    }


}
