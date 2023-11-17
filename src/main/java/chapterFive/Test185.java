package chapterFive;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test185 {
    public static void main(String[] args) throws Exception {

        Unsafe unsafe = getUnsafe();
        //获取偏移地址
        long name = unsafe.objectFieldOffset(Student1.class.getDeclaredField("name"));
        long url = unsafe.objectFieldOffset(Student1.class.getDeclaredField("url"));
        Student1 student1 = new Student1("1","1");
        unsafe.compareAndSwapObject(student1,name,"1","2");
        unsafe.compareAndSwapObject(student1,url,"1","4");
        System.out.println(student1);
    }

    public static  Unsafe getUnsafe() {
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        theUnsafe.setAccessible(true);
        Unsafe unsafe= null;
        try {
            unsafe = (Unsafe)theUnsafe.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
class Student1{
    volatile String name;
     volatile  String url;

    public Student1(String name,String url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}