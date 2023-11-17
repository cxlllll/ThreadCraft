package charterfour;

public class Test118 {
    private static int count = 10;

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                while(count >0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count--;
                    System.out.println(count);
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while(count<20){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    System.out.println(count);
                }
            }
        }.start();
    }
}
