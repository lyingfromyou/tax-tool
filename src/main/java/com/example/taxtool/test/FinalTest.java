package com.example.taxtool.test;

import cn.hutool.extra.pinyin.PinyinUtil;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class FinalTest {

    public void t1() {
        System.err.println("t1");
    }

    public void t2() {
        System.err.println("t2");
    }

    public void t3() {
        System.err.println("t3");
    }

    public final void t4() {
        System.err.println("t4");
    }


    public static void main(String[] args) {
//        Test1 t = new Test1();
//        t.t1();
//        t.t2();
//        t.t3();
//        t.t4();
//        String  s = PinyinUtil.getFirstLetter("是否卡拉啊垃圾螺丝钉解放拉到健身房", "");
//        System.err.println(s);

//        final Semaphore semaphore = new LocalSemaphore(3);
//
//
//        new Semaphore(3);
//
//        ExecutorService service = Executors.newFixedThreadPool(100);
//
//        IntStream.range(0, 10).forEach(i -> {
//            Runnable r = () -> {
//                try {
//                    try {
//                        semaphore.acquire();//请求获得许可，如果有可获得的许可则继续往下执行，许可数减1。否则进入阻塞状态
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//
//
//                    Thread.sleep(3000);
//                    semaphore.release();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            };
//            service.execute(r);
//        });

//        String s = "asdf";
//        while (true) {
//            s += s;
//        }
        Map<Test1, String> map = new TreeMap<>();


        map.put(new Test1(), "1");
        map.put(new Test1(), "2");


        System.err.println(map);
    }

}


class Test1 extends FinalTest {

}


class LocalSemaphore extends Semaphore {

    public LocalSemaphore(int permits) {
        super(permits);
    }

    @Override
    public void acquire() throws InterruptedException {
        super.acquire();
        System.out.println("线程" + Thread.currentThread().getName() +
                "进入，当前已有" + (3 - this.availablePermits()) + "个并发");
    }

    @Override
    public void release() {
        super.release();
        System.out.println("线程" + Thread.currentThread().getName() +
                "退出，当前已有" + (3 - this.availablePermits()) + "个并发");
    }
}