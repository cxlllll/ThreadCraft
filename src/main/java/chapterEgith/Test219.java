package chapterEgith;

import java.util.concurrent.*;

public class Test219 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<String> submit1 = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "1";
            }
        });
        pool.shutdown();

        try {
            Future<String> submit = pool.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "1";
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(submit1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
