package charterfour;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Test74 {
    public static void main(String[] args) throws InterruptedException {
        Account a  =new Account(1000);
        Account b = new Account(2000);
        log.info("开始");
         Thread t1=   new Thread(()->{
                for(int a1 = 0;a1<100000;a1++) {
                    a.transfer(b, getRandom());
                }
            });
         t1.start();
        Thread t2=   new Thread(()->{
            for(int a1 = 0;a1<100000;a1++) {
                b.transfer(a, getRandom());
            }
        });
        t2.start();
        t1.join();
        t2.join();
        log.info("结束"+"两人总共的钱"+(a.getMoney()+b.getMoney()));
        };
    private static  int getRandom(){
        return  new Random().nextInt(5)+1;
    }
}

/**
 * 创建账户类
 */
class  Account{
    private  int money;

    public Account(int account){
        this.money = account;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * 转账操作
     * @param amount
     */
    public  void transfer(Account target,int amount){
     synchronized (Account.class) {   // 必须把锁加在这个类上，
    if (this.money >= amount) {
        this.setMoney(money - amount);
        target.setMoney(target.getMoney() + amount);
    }
}
    }
}
