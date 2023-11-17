package chapterThree;

import lombok.extern.slf4j.Slf4j;


/**
 * 需要等待多少秒
 */
@Slf4j
public class Test33 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        t2.start();
        log.info("t1---join");
        t1.join();
        log.info("t2---join");
        t2.join();
        log.info("完成");
    }
}
