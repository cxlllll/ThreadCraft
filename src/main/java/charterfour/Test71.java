package charterfour;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
@Slf4j
public class Test71 {
    public static void main(String[] args) {
        TicketWindow ticketWindow =new TicketWindow(1000);
        List<Integer> list = new Vector<>(); // 统计票数
        List<Thread> threadList = new ArrayList<>();
        for(int a =0;a<40;a++){
            Thread thread=new Thread(()->{
                int i = ticketWindow.sellTicket(getRandom());
                list.add(i);
                // 上面这两行是不同共享变量的多线程操作组合，所以不需要关注这个组合的线程安全问题，但是要是对同一变量的组合操作需要考虑线程安全问题
            });
            thread.start();
            threadList.add(thread);// 这个list为什么不需要使用Vector是因为，这里只有主线程使用了他，
        }
        // 等待所有线程结束
        for(Thread thread :threadList){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("余票:"+ticketWindow.getCount());
        log.info("卖出票数："+list.stream().mapToInt(i->i).sum());


    }

    private static  int getRandom(){
        return  new Random().nextInt(5)+1;
    }
}
class  TicketWindow{
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TicketWindow(int count){
        this.count= count;
    }

    // 买票 amount张
    public synchronized int sellTicket(int amount){
        if(this.count>=amount){
            this.count -=amount;
            return amount;
        }else {
            return 0;
        }
    }
}