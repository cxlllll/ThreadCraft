package charterfour;

/**
 * 死锁问题复现：
 */
public class Test15 {
    private final static Object b1 = new Object();
    private final static Object b2 = new Object();

    public static void main(String[] args) {
        Thread a = new Thread(()->{
            synchronized (b1){
                try {
                    Thread.sleep(1000L);
                    synchronized (b2){

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        a.start();
        Thread b = new Thread(){
            @Override
            public void run() {
                synchronized (b2){
                    try {
                        Thread.sleep(1000L);
                        synchronized (b1){

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        b.start();
    }
}
