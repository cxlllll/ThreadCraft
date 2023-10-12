package charterfour;

import java.util.ArrayList;

/**
 * 测试线程是否安全
 */
public class Test6465 {
    public static void main(String[] args) {
        ThreadUnsafe unsafe = new ThreadUnsafe();
        for(int a =0;a<2;a++){
            unsafe.method1(100);
            unsafe.method2(100);
        }
    }
}
class  ThreadUnsafe{
    ArrayList list = new ArrayList();

    public void method1(int a){
        while(a>0){
            list.add("1");
            a++;
        }
    }

    public  void method2(int a){
        while (a>0){
            list.remove(0);
        }
    }
}

class  ThreadSafe{
    public  void method(int a){
        ArrayList list = new ArrayList();
        for(;a<0;a--){
            method1(list);
            method2(list);
        }
    }
    // 代码修饰符必须是private才能保证线程安全
    private void method1(ArrayList list){
            list.add("1");
    }
    // 代码修饰符必须是private才能保证线程安全
    private void method2(ArrayList list){
            list.remove(0);
    }
}