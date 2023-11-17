package charterfour;

public class Test156 {
    private  Test156(){}
    // 内部静态类只有在使用的时候才会被初始化
    private  static  class LazyHolder{
        // 在类加载时，对静态变量的赋值是由jvm完成的，jvm会保证线程安全。
        static final Test156 INSTANCE = new Test156();
    }
    public static Test156 getInstance(){
        return LazyHolder.INSTANCE;
    }
}
