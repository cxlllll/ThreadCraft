package charterfour;

/**
 * 三个线程交替输出，如abcabcabcabc
 */
public class Test130 {
    public static void main(String[] args) {
//        Thread t1 = new Thread(()->{
//            synchronized (flag){
//                while(flag==1){
//
//                    System.out.println("a");
//                    flag=2;
//                    flag.notifyAll();
//                }
//                t1.wa
//
//            }
//        },"小亮");
//        t1.start();
//        Thread t2 = new Thread(()->{
//            synchronized (flag){
//                while(flag==2){
//
//                    System.out.println("b");
//                    flag=3;
//                    flag.notifyAll();
//                }
//
//            }
//        },"小明");
//        t2.start();
//        Thread t3 = new Thread(()->{
//            synchronized (flag){
//                while(flag==3){
//                    System.out.println("c");
//                    flag=1;
//                    flag.notifyAll();
//                }
//            }
//        },"小黄");
//        t3.start();
        WaitNotify waitNotify = new WaitNotify(1,3);
        Thread thread = new Thread(){
            @Override
            public void run() {
                waitNotify.print("a",1,2);
            }
        };
        thread.start();
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                waitNotify.print("b",2,3);
            }
        };
        thread1.start();
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                waitNotify.print("c",3,1);
            }
        };
        thread2.start();
    }
}
class WaitNotify{
    private  int flag;
    private  int loopNumber; // 循环次数
    public WaitNotify(int a,int b){
        this.flag = a;
        this.loopNumber = b;
    }
    public void print(String str,int waitFlag,int nextFlag) {
        for(int a = 0;a<loopNumber;a++){
            synchronized (this){
                while(waitFlag != flag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }

}
