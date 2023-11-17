package chapterFive;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * 手写AtomicInteger的updateAndGet方法
 */
public class Test168 {
    public static void main(String[] args) {

    }

    public int  updateAndGet(AtomicInteger i){
        while(true){
            int prev = i.get();
            int next = prev * 10;
            i.compareAndSet(prev,next);
        }
    }

    public int  updateAndGetTwo(AtomicInteger i, IntUnaryOperator operator){
        while(true){
            int prev = i.get();
            // 为了让这个updateAndGet具有复用性，就是让这个操作不写死 所以应用这个接口 ，接口的方法就是传入一个int，返回一个int
            int next = operator.applyAsInt(prev);
            i.compareAndSet(prev,next);
        }
    }
}
