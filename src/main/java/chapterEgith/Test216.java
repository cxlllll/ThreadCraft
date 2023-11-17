package chapterEgith;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
@Slf4j
public class Test216 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        log.info("begin");
                        Thread.sleep(1000);
                        return "1";
                    }
                },
                new Callable<String>(){
                    @Override
                    public String call() throws Exception {
                        log.info("begin");
                        Thread.sleep(800);
                        return "2";
                    }
                },
                ()->{
                    log.info("begin");
                    Thread.sleep(2000);
                  return "3";
                }
        ));
        futures.forEach(f->{
            try {
                String s = f.get();
                log.info(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
