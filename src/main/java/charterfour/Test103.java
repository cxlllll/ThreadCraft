package charterfour;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * 使用保护性暂停---这里
 */
@Slf4j
public class Test103 {
    public static void main(String[] args) {
        for (int a = 0;a<3;a++){
            Person person = new Person();
            person.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int id :MailBoxs.getIds()){
            new PostMan(id,"寄信内容---"+id).start();
        }
    }

}
// 模拟人收信
@Slf4j
class Person extends  Thread{

    @Override
    public void run() {
        GuardedLog guardedLog = MailBoxs.generateGuarded();
        log.info("取信的id:"+guardedLog.getId());
        Object obj = guardedLog.getObj(5000);// 取信过程
        log.info("取信的id:"+guardedLog.getId()+" 取信内容："+obj);
    }
}
@Slf4j
class PostMan extends  Thread{
    private int id;// 用来关联中间对象 GuardedLog
    private String emial;// 用来存储信的内容



    public PostMan(int id,String emial){
this.id = id;
this.emial = emial;
    }
    @Override
    public void run() {
        //送信
        GuardedLog guardedLogById = MailBoxs.getGuardedLogById(this.id);
        guardedLogById.completed("送信"+guardedLogById.getId());
        log.info(id+"已经送信内容为  "+emial);
    }
}
@Slf4j
class  MailBoxs{
    static Map<Integer,GuardedLog>  guardedLogs= new Hashtable<>();
    private  static int i = 1;
    public synchronized static int gernerateId(){
        return i++;
    }
    // 再把信件的产生控制在自己这个类
    public static  GuardedLog generateGuarded(){
        GuardedLog guardedLog = new GuardedLog(gernerateId());
        guardedLogs.put(guardedLog.getId(),guardedLog);
        return  guardedLog;
    }

    // 查看邮件中的信的id的集合
    public  static Set<Integer> getIds(){
        return  guardedLogs.keySet();
    }

    public static GuardedLog getGuardedLogById(int id){
        return guardedLogs.remove(id);// 如果不使用 remove那么可能会发生内存溢出的情况。
    }

}
@Slf4j
class GuardedLog{

    private int id;
    private Object object;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GuardedLog(int id){
        this.id  =id;
    }

    /**
     * 增加等待时间 n秒
     */
    public Object getObj(long n){
        synchronized (this){
            long begin = System.currentTimeMillis();
            while(object==null){
                long experienceTime = 0;//经历的时间
                if(experienceTime > n){
                    break;// 如果大于了这个时间 那么跳出循环
                }
                try {
                    this.wait(n-experienceTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                experienceTime = System.currentTimeMillis() - begin;
            }
            // 不为空就执行代码
            return object;
        } }

    public void completed(Object obj){
        synchronized (this){
            this.object = obj;
            this.notifyAll();
        }
    }

}