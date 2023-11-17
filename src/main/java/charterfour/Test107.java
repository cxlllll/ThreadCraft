package charterfour;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 生产者与消费者队列
 */
@Slf4j
public class Test107 {

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);
        for(int i = 0;i<3;i++){
            int a = i;
            new Thread(()->{
                messageQueue.put(a,"我最牛"+a);
            },i+"线程").start();
        }
        new Thread(()->{
            while(true){
                log.info("一共进来多少次");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                messageQueue.take();
            }

        },"消费者").start();
    }
}
@Slf4j
class MessageQueue{
    LinkedList<Message<String>> list = new LinkedList<>();// 使用双向队列来模拟消息队列
    private int capicity;// 容量
    public MessageQueue(int capicity){
        this.capicity = capicity;
    }
    // 生产消息
    public void put(int id,String msg){
        synchronized (list){
            while(list.size()==capicity){
                log.info("队列已满，听着生产");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("生产消息"+id+msg);
            list.add(new Message<>(id,msg));
            list.notifyAll();
        }
    }
    // 消费消息
    public Message<String> take(){
        synchronized (list){
            while(list.isEmpty()){
                try {
                    log.info("消息队列为空");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message<String> message = list.removeFirst();
            log.info("消费消息"+message.getId()+message.getObj());
            list.notifyAll();
            return message;
        }
    }
}
class Message<T>{
    private int id; // 消息id，唯一性来保证一条消息对应一个id
    private T obj;

    public int getId() {
        return id;
    }

    public T getObj() {
        return obj;
    }

    public Message(int id,T taeget){
        this.id = id;
        this.obj = taeget;
    }
}
