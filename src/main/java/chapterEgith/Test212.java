package chapterEgith;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Test212 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(()->{
            log.info("1");
        });
        pool.execute(()->{
            log.info("2");
        });
        pool.execute(()->{
            log.info("3");
        });

        // 自定义线程的名字
        Executors.newFixedThreadPool(3, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"tfl"+index.getAndIncrement()+"-thread");
            }
        });
    }
    public void test(){


    }}
