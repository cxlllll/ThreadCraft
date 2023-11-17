package chapterFive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用不加锁实现银行账户扣款问题
 */
public class TestAccount {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        Account account = new Account(1000);
        for(int a=0;a<100;a++){
            Thread thread = new Thread() {
                @Override
                public void run() {
                    account.withDraw(10);
                }
            };
            list.add(thread);
        }
        for(Thread thread:list){
            thread.start();
        }
        for(Thread thread:list){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(account.getBalance());
    }
}
class Account{
    private AtomicInteger balance;

    public int getBalance() {
        return balance.get();
    }

    public void setBalance(AtomicInteger balance) {
        this.balance = balance;
    }

    public Account(int balance){
        this.balance = new AtomicInteger(balance);
    }

    public void withDraw(Integer draw){
//        while (true){
//            //
//            int prev = balance.get();
//            int next = prev-draw;
//            boolean b = balance.compareAndSet(prev, next);
//            if(b){
//                break;
//            }
//        }
        balance.getAndAdd(-1*draw);// 这一句话也可以代替上面这一段代码
    }
}