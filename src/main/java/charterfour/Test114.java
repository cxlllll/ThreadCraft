package charterfour;

import lombok.extern.slf4j.Slf4j;

/**
 * 多把锁测试
 */
@Slf4j
public class Test114 {
    private final Object studyRoom = new Object();
    private final Object bedRoom = new Object();

    public void study(){
        synchronized (studyRoom){
            try {
                Thread.sleep(1000);
                log.info("学习一秒钟");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sleep(){
        synchronized (bedRoom){
            try {
                Thread.sleep(2000);
                log.info("睡眠两秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
