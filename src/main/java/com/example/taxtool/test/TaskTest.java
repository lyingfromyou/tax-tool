package com.example.taxtool.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author by Lying
 * @Date 2019/8/18
 */
public class TaskTest {

   static final BlockingQueue blockingQueue = new ArrayBlockingQueue<>(10);
    static final  ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(10, 20, 1, TimeUnit.MINUTES, blockingQueue);


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Long start = System.currentTimeMillis();
        Collection<TestTask> list = new ArrayList<>();
        IntStream.range(0, 20).forEach(i -> list.add(new TestTask()));

        List<Future<String>> futures = threadPoolExecutor.invokeAll(list);

        System.err.println("一共执行花了: " + (System.currentTimeMillis() - start) );
        for (Future<String> future : futures) {
            if (future.isDone()) {
                System.err.println(future.get());
            }
        }

        System.err.println("ok");
        threadPoolExecutor.shutdown();
    }



}


class TestTask implements Callable<String> {


    @Override
    public String call() throws Exception {
        System.err.println(Thread.currentThread().getName() + "  -- do some thing");
        Thread.sleep(5000);

        return "im " + Thread.currentThread().getName() + ", 我 over 了";
    }
}
