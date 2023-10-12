package charterfour;

import chapterThree.Test48;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 测试类锁与对象锁
 * ClassMonitor中的两个方法不互斥 ，谁有可能先执行，一个是对象锁，一个是类锁
 */
public class Test62 {
    public static final Log log = LogFactory.getLog(Test62.class);
    public static void main(String[] args) {
        ClassMonitor monitor = new ClassMonitor();
        new Thread(()->{
            monitor.addA();
        }).start();
        new Thread(()->{
            ClassMonitor.add();
        }).start();
    }

}
class ClassMonitor{
    public static final Log log = LogFactory.getLog(ClassMonitor.class);
    public synchronized void addA(){
        log.info("object");
    }

    public static  synchronized void add(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("class");
    }
}