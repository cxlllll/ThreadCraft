package charterfour;

import java.util.ArrayList;

/**
 * ThreadTest重写了ThreadSafe的非私有的方法导致了线程不安全
 */
public class Test66 {

}
class  ThreadSafeNew{
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
    public void method2(ArrayList list){
        list.remove(0);
    }
}
class ThreadTest extends ThreadSafeNew{
    @Override
    public void method2(ArrayList list) {
        new Thread(()->{
            list.remove(0);
        });
    }
}