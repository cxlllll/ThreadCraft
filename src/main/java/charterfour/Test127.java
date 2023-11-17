package charterfour;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class Test127 {
    private static boolean yanFlag= false;
    private  static boolean waterFlag = false;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionYan = lock.newCondition();
    private static Condition conditionWater = lock.newCondition();

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                lock.lock();
                try{
                    while(!yanFlag){
                        try {
                            log.info("等烟中");
                            conditionYan.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    log.info("烟到了，开始吸烟");
                }finally {
                    lock.unlock();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                lock.lock();
                try{
                    while (!waterFlag){
                        try {
                            log.info("等水中");
                            conditionWater.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    log.info("水到了，开始喝水");
                }finally {
                    lock.unlock();
                }
            }
        }.start();
        new Thread(()->{
            lock.lock();
            try {
                try {
                    Thread.sleep(1000);
                    log.info("开始送烟");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                yanFlag = true;
                conditionYan.signal();
            }finally {
                lock.unlock();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            lock.lock();
            try {
                try {
                    Thread.sleep(1000);
                    log.info("开始送水");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waterFlag = true;
                conditionWater.signal();
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
