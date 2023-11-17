package charterfour;

/**
 * 测试notify与notifyAll的区别
 */
public class Test90_2 {
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("进入到t1了");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完1开始执行其他代码");
            }
        }, "xiao");
        thread.start();
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("进入到t2了");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完2开始执行其他代码");
            }
        }, "xiaodong");
        thread2.start();
        new Thread(){
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("进入到线程三");
                   // lock.notify();
                    lock.notifyAll();
                }
            }
        }.start();
    }
}
