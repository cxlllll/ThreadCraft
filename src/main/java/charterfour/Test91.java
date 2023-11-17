package charterfour;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 测试sleep与wait的区别
 */
public class Test91 {
    static  final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                synchronized (lock){
                try {
//                    Thread.sleep(2000);
                    lock.wait(2000);
                    System.out.println("t1获得锁"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}
        }.start();
        System.out.println("主线程开始等待"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())));
        Thread.sleep(1000);
        synchronized (lock){
            System.out.println("t2获得锁"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())));
        }
    }
}
