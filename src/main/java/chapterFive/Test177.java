package chapterFive;

import java.util.concurrent.atomic.AtomicInteger;

/*
CAS锁   切记不要用于生产
 */
public class Test177 {
    private AtomicInteger lock = new AtomicInteger(0);

    public void lock(){
        while (true){
            boolean b = lock.compareAndSet(0, 1);
            if(b){
                break;
            }
        }
    }

    public void unlock(){
        // 因为这个方法只有获得锁的那一个线程使用，不会产生竞争，所以不用枷锁。
        lock.set(0);
    }
}
