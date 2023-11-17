package charterfour;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class Test124 {
    public static void main(String[] args) {
        ChopstickUseLock t1 = new ChopstickUseLock("t1");
        ChopstickUseLock t2 = new ChopstickUseLock("t2");
        ChopstickUseLock t3 = new ChopstickUseLock("t3");
        ChopstickUseLock t4 = new ChopstickUseLock("t4");
        ChopstickUseLock t5 = new ChopstickUseLock("t5");
        new PhilosopherUseLock(t1,t2,"硕仔1").start();
        new PhilosopherUseLock(t2,t3,"硕仔2").start();
        new PhilosopherUseLock(t3,t4,"硕仔3").start();
        new PhilosopherUseLock(t4,t5,"硕仔4").start();
        new PhilosopherUseLock(t5,t1,"硕仔5").start();
    }
}

class ChopstickUseLock extends ReentrantLock {
    private  String name;
    public ChopstickUseLock(String name ){
        this.name=name;
    }
}
@Slf4j
class PhilosopherUseLock extends  Thread{
    private ChopstickUseLock left;
    private ChopstickUseLock right;
    private String name;

    public   PhilosopherUseLock(ChopstickUseLock left,ChopstickUseLock right,String name){
        this.left = left;
        this.right = right;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
              if(left.tryLock()){
                  try {
                      if(right.tryLock()){
                          try {
                              estFood();
                          }finally {
                              right.unlock();
                          }
                      }
                  }finally {
                      left.unlock();
                  }
              }
            }
        }


    public  void estFood(){
        log.info(this.name+"开始吃东西");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}