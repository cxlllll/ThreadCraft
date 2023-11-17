package charterfour;

import java.util.concurrent.locks.LockSupport;

/**
 * 设计模式之线程固定顺序
 * 这里需要注意的就是不一定哪个线程先运行，
 */
public class Test129 {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                LockSupport.park();
                System.out.println("1");
            }
        };
        t1.start();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("2");
                LockSupport.unpark(t1);

            }
        };
        t2.start();
    }
}

