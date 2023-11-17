package chapterThree;

import chapterone.CreateThread;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test25 {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                log.info(this.getState().name());
            }
        };
        log.info(t1.getState().name());
        t1.start();
        log.info(t1.getState().name());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
