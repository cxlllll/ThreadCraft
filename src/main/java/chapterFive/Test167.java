package chapterFive;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Test167 {
    public static void main(String[] args) {
        AtomicInteger integer  = new AtomicInteger(100);
        System.out.println(integer.getAndIncrement());//加1
        System.out.println(integer.incrementAndGet());
        System.out.println(integer.addAndGet(5));//加5
        System.out.println(integer.updateAndGet(value->value*5));// 乘法
        Map<String,String> map = new HashMap<String,String>();
        map.put("123","123");
        for (String str :map.keySet()){
            System.out.println(str);
        }
        map.clear();
        System.out.println(map.size());
        Integer a= new Integer(1);
        System.out.println(a.toString());
    }
}
