package charterfour;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用await和notify来实现交替输出
 */
public class Test131 {
    public static void main(String[] args) {
        AwaitNotify awaitNotify =new AwaitNotify(3);
        Condition condition = awaitNotify.newCondition();
        Condition condition1 = awaitNotify.newCondition();
        Condition condition2 = awaitNotify.newCondition();
       Thread t1= new Thread(){
            @Override
            public void run() {
                awaitNotify.print("a",condition,condition1);
            }
        };
       t1.start();
        new Thread(){
            @Override
            public void run() {
                awaitNotify.print("b",condition1,condition2);
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                awaitNotify.print("c",condition2,condition);
            }
        }.start();
            awaitNotify.lock();
            condition.signal();
            awaitNotify.unlock();

    }


}
class  AwaitNotify extends ReentrantLock{
    private  int loopNumber;
    public int getLoopNumber() {
        return loopNumber;
    }

    public void setLoopNumber(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public AwaitNotify(int loopNumber){
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition condition,Condition nextCondition){
        for(int a=0;a<loopNumber;a++){
            this.lock();
            try{
                condition.await();
                System.out.println(str);
                nextCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.unlock();
            }
        }
    }
}
