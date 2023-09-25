package chapterone;

/**
 * 线程创建方式
 */
public class CreateThread {
    public static void main(String[] args) {
        // 方式1
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("无语了");
            }
        };
        thread.start();

        // 方式二
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("方式二");
            }
        };
        new Thread(run1,"run2").start();
    }
}
