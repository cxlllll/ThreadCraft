package chapterThree;

/**
 * 测试守护线程
 */
public class Test43 {
    public static void main(String[] args) {
        Thread testDamen = new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("疯狂执行代码");
                }
            }
        };
        testDamen.setDaemon(true);// 设置为守护线程
        testDamen.start();
        System.out.println("主线程已经完成");
    }
}
