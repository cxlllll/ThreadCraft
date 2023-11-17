package chapterSix;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Test194 {

}
class Pool{
    private final  int poolSize;//连接池数量大小
    private Connection[] connections;
    private AtomicIntegerArray status;

    public Pool(int value){
        this.poolSize= value;
        connections = new Connection[value];
        status=new AtomicIntegerArray(value);
    }

    //使用CAS获取连接
    public Connection getConnection(){
        while(true){
            for(int initIndex = 0;initIndex <poolSize;initIndex++){
                if(status.get(initIndex)==0){
                    if(status.compareAndSet(initIndex,0,1)){
                        return connections[initIndex];
                    }
                }
            }
            // 循环一轮以后 此线程进入等待
            synchronized (this){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void freeConnection(Connection conn){
        for(int a=0;a<poolSize;a++){
            if(connections[a]==conn){
                status.set(a,0);// 为什么这里不用sychronized与CAS是因为，释放这个conn是当前线程持有，没有发生竞争。所以它可以直接设置成0
            }
        }
        synchronized (this){
            this.notifyAll();//唤醒沉睡线程
        }
    }
}