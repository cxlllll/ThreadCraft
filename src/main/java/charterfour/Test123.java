package charterfour;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class Test123 {
    private  static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    if(!lock.tryLock(2, TimeUnit.SECONDS)){
                        log.info("没有获取到锁");
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.info("被打断了");
                    return;
                }try{
                log.info("获取锁");}
                finally {
                    lock.unlock();//释放锁
                }
            }
        };
        lock.lock();
        t1.start();
        Thread.sleep(1000);
        lock.unlock();
    }
}
