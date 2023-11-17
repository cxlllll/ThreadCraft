package chapterThree;

import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.locks.LockSupport;
@Slf4j
public class Test41 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                log.info("线程开始运行");
                log.info("线程开始停止");
                LockSupport.park();;
                log.info("线程打断结束"+Thread.currentThread().isInterrupted());
                log.info("线程继续停止");
                LockSupport.park();
                log.info("线程停止失效");
            }
        };
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
