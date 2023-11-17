package chapterThree;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test36 {
    public static void main(String[] args) {
        Thread t1= new Thread(){
            @Override
            public void run() {
                while(true){
                    boolean interrupterFalg = Thread.currentThread().isInterrupted();
                    if(interrupterFalg){
                        log.info("此线程被打断");
                        break;
                    }
                }
            }
        };
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("开始打断");
        t1.interrupt();

    }
}
