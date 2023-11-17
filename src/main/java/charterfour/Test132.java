package charterfour;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用park/unpark实现交替输出
 */
public class Test132 {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) throws InterruptedException {
        TestParkUnpark testParkUnpark = new TestParkUnpark(3);
        t1 =new Thread(()->{
            testParkUnpark.print(t2,"a");
        });
   //     t1.start();
        t2 =new Thread(()->{
            testParkUnpark.print(t3,"b");
        });
      //  t2.start();
        t3 =new Thread(()->{
            testParkUnpark.print(t1,"c");
        });
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(500);
        LockSupport.unpark(t1);
    }


}
class TestParkUnpark{
    private int loopNumber;

    public TestParkUnpark(int loopNumber){
       this.loopNumber = loopNumber;
    }

    public void print(Thread thread,String str){
        for(int i = 0;i<loopNumber;i++){
            LockSupport.park();
            System.out.println(str);
            LockSupport.unpark(thread);
        }
    }

}