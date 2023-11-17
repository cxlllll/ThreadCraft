package chapterFive;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class Test172 {
    static  AtomicMarkableReference<Packge> reference = new AtomicMarkableReference(new Packge("a"),true);
    public static void main(String[] args) throws InterruptedException {
        // 主线程代表主人

        Packge prev = Test172.reference.getReference();
        // 保洁阿姨看到垃圾满了就到垃圾
        new Thread(()->{
            Packge bag = Test172.reference.getReference();
            reference.compareAndSet(bag,bag,true,false);
        });
        Thread.sleep(100);
        Test172.reference.compareAndSet(prev,new Packge("新的垃圾袋"),true,false);
    }
}
class Packge{
    private  String str;// 描述垃圾袋是否为空

    public Packge(String s){
        this.str = s;

    }    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Packge{" +
                "str='" + str + '\'' +
                '}';
    }
}
