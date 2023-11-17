package chapterThree;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test27 {
    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(){
           @Override
           public void run() {
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   System.out.println("我进来了");
               }
           }
       };
       t1.start();
       Thread.sleep(500);
       t1.interrupt();
    }
}
