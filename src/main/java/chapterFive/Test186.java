package chapterFive;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 自定义实现原子整数类
 */
public class Test186 {

}
class MyAtomicInteger{
    private  volatile  int value;
    private  static  final    long offset;
    private  static  final Unsafe unsafe;

    static {
        unsafe=Test185.getUnsafe();
        try {
            offset = unsafe.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("报错");
        }

    }

    public int getValue(){
        return value;
    }

    public void  compareAndSet(int value){
        while (true){
            int prev = this.value;
            int nowValue = value-prev;
            if(unsafe.compareAndSwapObject(this,offset,prev,nowValue)){
                break;
            }        }
    }
}