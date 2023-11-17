package charterfour;

import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.locks.LockSupport;

/**
 * 测试park和unpark
 */
@Slf4j
public class Test108 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            log.info("开始运行");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("开始打断");
            LockSupport.park();
            log.info("恢复运行");
        },"t1");
        t1.start();
//        Thread.sleep(2000);
        Thread.sleep(1000);
        log.info("主线程unpark");
        LockSupport.unpark(t1);
    }
}
