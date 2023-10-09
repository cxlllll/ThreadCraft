package chapterThree;

public class Test32 {
    static  int a =0;
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a= 10;
            }
        };
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
