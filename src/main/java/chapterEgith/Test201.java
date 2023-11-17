package chapterEgith;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test201 {

}
class BlockingQueue<T>{
    private Deque<T> deque=new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition fullSet= lock.newCondition();
    private Condition emptySet= lock.newCondition();
    private int capicity;
    public BlockingQueue(int length){
        this.capicity = length;
    }
    //从队列里夺取元素
    public T take(){
        lock.lock();
        try {
            while(deque.isEmpty()){
                //当队列空的时候 线程进行休息
                try {
                    emptySet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullSet.signalAll();
            return  t;
        }finally {
            lock.unlock();
        }
    }

    // 带超时的获取
    //从队列里夺取元素
    public T pull(long timeout, TimeUnit unit){
        lock.lock();
        try {
            long naNos = unit.toNanos(timeout);
            while(deque.isEmpty()){
                //当队列空的时候 线程进行休息
                try {
                    naNos = emptySet.awaitNanos(naNos);
                    if(naNos <=0){
                        return null;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullSet.signalAll();
            return  t;
        }finally {
            lock.unlock();
        }
    }

    public void put(T task){
        lock.lock();
        try {
            while(deque.size()==capicity){
                // 当队列满了 生产者需要暂停
                try {
                    fullSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(task);
            emptySet.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public boolean offer(T task,long timeOut,TimeUnit unit){
        lock.lock();
        try {
            long l = unit.toNanos(timeOut);
            while(deque.size() == capicity){
                try {
                   l= fullSet.awaitNanos(l);
                   if(l<=0){
                       return false;
                   }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(task);
            emptySet.signalAll();
            return true;
        }finally {
            lock.unlock();
        }
    }

    public int getSize(){
        lock.lock();
        try{
            return deque.size();
        }finally {
            lock.unlock();
        }
    }
}
class ThreadPool{
    private BlockingQueue<Runnable> queue;
    private HashSet<Work> works = new HashSet<>();// 存放线程
    private int threadSize;// 核心线程数
    private  TimeUnit unit;// 时间单位

    public ThreadPool(int size,int threads,TimeUnit unit){
        queue = new BlockingQueue<>(size);
        threadSize = threads;
        this.unit = unit;
    }

    public void execute(Runnable task){
        synchronized (works){
            if(threadSize>= works.size()){
                //说明还有空闲得线程位置
                Work work = new Work(task);
                works.add(work);
                work.start();
            }else{
                queue.put(task);
            }
        }
    }

    class Work extends Thread{
        private  Runnable task;
        public Work(Runnable task){
            this.task = task;
        }

        @Override
        public void run() {
            while(task!=null || ((task= queue.take()) != null)){
                try {
                    task.run();
                    // 因为线程是需要重复利用的 所以task置为空 继续执行
                }finally {
                    task = null;
                }
            }
            synchronized (works){
                works.remove(this);
            }
        }
    }
}