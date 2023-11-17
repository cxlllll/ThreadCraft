package charterfour;

/**
 * 使用volatile进行两阶段中止模式
 */
public class Test138 {
    private  volatile static boolean flag = false;
    private  Thread monitor;// 监控线程
    public static void main(String[] args) {
        Test138 test138 = new Test138();
        test138.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test138.stop();
    }
    public void start(){
        monitor =new Thread() {

            @Override
            public void run() {
                while(true){
                    if(flag){
                        System.out.println("退出循环");
                        break;
                    }


                    try {
                        Thread.sleep(1000);
                        System.out.println("进行监控");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        monitor.start();
    }
    public void stop(){
        flag = true;
        Thread.currentThread().interrupt();
    }
}
