package charterfour;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class Test122 {

    private  static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.info("打断成功");
                    return;
                }
                try {
                    log.info("进行测试");
                }
                finally {
                    lock.unlock();
                }
            }
        };
        lock.lock();
        log.info("主线程获取锁成功");
        t1.start();

        Thread.sleep(100);
        t1.interrupt();
    }
}
