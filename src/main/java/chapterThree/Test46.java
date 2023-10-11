package chapterThree;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Test46 {
    public static final Log log = LogFactory.getLog(Test46.class);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {

            }
        };

        final Thread t2 = new Thread(){
            @Override
            public void run() {
                while (true){

                }

            }
        };
        t2.start();

        Thread t3 = new Thread("t3"){
            @Override
            public void run() {
                log.info("完成");
                                 }
        };
        t3.start();

        Thread t4 = new Thread("t4"){
            @Override
            public void run() {
                synchronized (Test46.class){
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}
        };
        t4.start();
        Thread t5 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        t5.start();
        Thread t6 = new Thread(){
            @Override
            public void run() {
                synchronized(Test46.class){
                    while (true){

                    }
                }
            }
        };
        t6.start();
        Thread.sleep(50);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        System.out.println(t3.getState());
        System.out.println(t4.getState());
        System.out.println(t5.getState());
        System.out.println(t6.getState());
    }
}
