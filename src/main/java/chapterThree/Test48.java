package chapterThree;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 泡茶例题
 */
public class Test48 {
    public static final Log log = LogFactory.getLog(Test48.class);
    public static void main(String[] args) {
        Thread xiaoWang = new Thread(()->{
            try {
                log.info("拿水壶");
                Thread.sleep(1000);
                log.info("烧水--15分钟");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"小王");
        xiaoWang.start();
        Thread laoWang = new Thread(()->{
            try{
                log.info("拿茶壶--一分钟");
                Thread.sleep(1000);
                log.info("拿茶叶---1分钟");
                Thread.sleep(1000);
                log.info("洗茶叶--两分钟");
                Thread.sleep(2000);
                xiaoWang.join();
                log.info("泡茶");
            }catch (Exception e){
                e.printStackTrace();
            }
        },"老王");
        laoWang.start();
    }
}
