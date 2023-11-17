package chapterThree;


import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段停止模式使用----监控类实现
 */
@Slf4j
public class Test37 {
    Thread monitor;
    public  void start(){
       monitor = new Thread(){
           @Override
           public void run() {
               while (true){
                   boolean interrupterFlag  =Thread.currentThread().isInterrupted();
                   if(interrupterFlag){
                       log.info("被打断了请注意");
                       break;
                   }
                   try {
                       Thread.sleep(500);
                       log.info("执行监控");
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                       Thread.currentThread().interrupt();// 因为在睡眠时打断打断标记会被清楚
                   }
               }
           }
       };
       monitor.start();
    }

    public void stop(){
        monitor.interrupt();
    }
}
