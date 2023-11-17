package charterfour;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test117 {
    public static void main(String[] args) {
        Chopstick t1 = new Chopstick("t1");
        Chopstick t2 = new Chopstick("t2");
        Chopstick t3 = new Chopstick("t3");
        Chopstick t4 = new Chopstick("t4");
        Chopstick t5 = new Chopstick("t5");
        new Philosopher(t1,t2,"硕仔1").start();
        new Philosopher(t2,t3,"硕仔2").start();
        new Philosopher(t3,t4,"硕仔3").start();
        new Philosopher(t4,t5,"硕仔4").start();
        new Philosopher(t5,t1,"硕仔5").start();
    }
}
class Chopstick{
    private  String name;
    public Chopstick(String name ){
        this.name=name;
    }
}
@Slf4j
class Philosopher extends  Thread{

    private Chopstick left;
    private Chopstick right;
    private String name;

    public   Philosopher(Chopstick left,Chopstick right,String name){
        this.left = left;
        this.right = right;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            synchronized (left){
                log.info(name+"获得了左筷子");
                synchronized (right){
                    log.info(name+"获得了右筷子");
                    estFood();
                }
            }
        }
    }

    public  void estFood(){
        log.info("开始吃东西");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}