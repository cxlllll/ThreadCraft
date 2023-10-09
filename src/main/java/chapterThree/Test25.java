package chapterThree;

import chapterone.CreateThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Test25 {
    private  static Log log = LogFactory.getLog(Test25.class);
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                log.info(this.getState());
            }
        };
        log.info(t1.getState());
        t1.start();
        log.info(t1.getState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
