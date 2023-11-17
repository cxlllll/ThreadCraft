package chapterEgith;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Test225 {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.schedule(()->{
            log.info("1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("end");
        },4, TimeUnit.SECONDS);
        pool.schedule(()->{
            log.info("2");
        },1, TimeUnit.SECONDS);


//        Timer timer = new Timer();
//        TimerTask task1 = new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                log.info("1");
//            }
//        };
//        TimerTask task2 = new TimerTask() {
//            @Override
//            public void run() {
//                log.info("2");
//            }
//        };
//        log.info("begin");
//        timer.schedule(task1,1000);
//        timer.schedule(task2,1000);
    }
}
