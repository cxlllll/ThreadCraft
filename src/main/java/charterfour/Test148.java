package charterfour;

/**
 * 使用volatile实现单例模式
 * 不加volatile的话会出现重排序的问题
 */
public final class Test148 {
    private Test148(){}
    private static volatile Test148 instance=null;
    public static Test148 getInstance(){
        if(instance == null){
        synchronized (Test148.class){
            if(instance ==null){
                instance= new Test148();
            }
        }
        }
        return instance;
    }

}
