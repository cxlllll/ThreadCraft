package chapterFive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Test169 {
    public static void main(String[] args) {

    }
}
class  DemicalAccountsCas{
    private AtomicReference<BigDecimal> ammount;

    public DemicalAccountsCas(BigDecimal decimal){
        ammount = new AtomicReference<>(decimal);

    }
    public BigDecimal getAmmount(){
        return ammount.get();
    }

    public void withDraw(BigDecimal sub){
        while (true){
            BigDecimal now = ammount.get();
            BigDecimal next = now.subtract(sub);
            if(ammount.compareAndSet(now, next)){
                break;
            }
        }
    }

    static void TestDemo(DemicalAccountsCas demicalAccountsCas){
        List<Thread> list = new ArrayList<>();
        for(int a =0;a<100;a++){
            list.add(new Thread(()->{
                demicalAccountsCas.withDraw(BigDecimal.TEN);
            }));
        }
        for (Thread t:list
             ) {
            t.start();
        }
        for(Thread t:list){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demicalAccountsCas.getAmmount());
    }
}
