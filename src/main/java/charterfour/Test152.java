package charterfour;

public class Test152 {
    static  int x;
    static Object object = new Object();
    // 线程解锁m之前对变量的写，对于接下来对object加锁的其他线程对该变量的读可见。
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                x = 10;
            }
        };  thread.start();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println(x);
            }
        };

        thread2.start();
    }


}
class TestVo{
    // 加上了volatile修饰的变量线程中修改变量的值对接下来他线程可见。（讲究线程执行的先后顺序）
    static  volatile  int x;
    public void test(){
        new Thread(){
            @Override
            public void run() {
                x =10;
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                System.out.println(x);
            }
        }.start();
    }
}