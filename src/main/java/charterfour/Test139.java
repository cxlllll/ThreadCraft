package charterfour;

/**
 * 犹豫模式
 */
public class Test139 {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.start();
        monitor.start();
        monitor.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitor.stop();
    }
}
class Monitor{
    private volatile boolean stopflag=false;
    private Thread thread;
    private volatile boolean onceFlag=false;
    // 这里涉及到tomcat线程对这个变量的读取，而修改这个变量的又是这里的thread线程，所以存在可见性问题，因此需要加上volatile

    public void  start(){
        synchronized (this){
            if(onceFlag){
                return;
            }
            onceFlag= true;
        }

        thread = new Thread(()->{
                        while(true){
            if(stopflag){
                System.out.println("停止监控");
                onceFlag= false;
                break;
            }
            System.out.println("执行监控");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }});
        thread.start();

    }

    public void stop(){
        stopflag = true;
        if(null!=thread){
            thread.interrupt();
        }

    }
}
