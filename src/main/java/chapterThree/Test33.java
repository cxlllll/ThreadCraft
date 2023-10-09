package chapterThree;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 需要等待多少秒
 */
public class Test33 {
    private  static Log log = LogFactory.getLog(Test33.class);
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
