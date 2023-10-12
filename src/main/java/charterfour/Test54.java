package charterfour;

/**
 * 使用snynchronized测试临界区代码
 */
public class Test54 {
    static  int i  =0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
           for(int a = 0;a<5000;a++){
               i--;
           }
        },"cat");

    Thread t2 = new Thread(()->{
        for (int b = 0;b<5000;b++){
            i++;
        }
    },"dog");
    t1.start();
    t2.start();
    // 等待两个线程运行结束
    t1.join();
    t2.join();
    System.out.println(i);
    };
}
