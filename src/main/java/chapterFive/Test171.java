package chapterFive;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决ABA问题 就是带版本号的多线程修改
 */
public class Test171 {
    static  AtomicStampedReference<String> stampedReference =new AtomicStampedReference<>("A",0);
    public static void main(String[] args) throws InterruptedException {
        int stamp = stampedReference.getStamp();
        System.out.println("版本号为"+stamp);
        other();
        Thread.sleep(100);
        boolean b = stampedReference.compareAndSet(stampedReference.getReference(), "B", stamp, stamp + 1);
        System.out.println("是否修改成功"+b);
    }

    private static void other() {
        new Thread(()->{
            int stamp = stampedReference.getStamp();
            String str = stampedReference.getReference();
            stampedReference.compareAndSet(str,"B",stamp,stamp+1);
        }).start();
        new Thread(()->{
            int stamp = stampedReference.getStamp();
            String str = stampedReference.getReference();
            stampedReference.compareAndSet(str,"A",stamp,stamp+1);
        }).start();
    }
}
