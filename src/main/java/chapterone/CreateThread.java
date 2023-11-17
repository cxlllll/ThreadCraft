package chapterone;


import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建方式
 */
@Slf4j
public class CreateThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 方式1
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("无语了");
            }
        };
        thread.start();

        // 方式二
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("方式二");
            }
        };
        new Thread(run1,"run2").start();

        //方式3 使用futuretask
        FutureTask<Integer> futureTask=new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("100");
                Thread.sleep(5000L);
                return 100;
            }
        });
        Thread futureTest = new Thread(futureTask,"futureTest");
        futureTest.start();
        Integer integer = futureTask.get();
        log.info(String.valueOf(integer));
    }



}
