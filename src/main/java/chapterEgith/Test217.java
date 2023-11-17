package chapterEgith;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class Test217 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        String s = null;
        try {
            s = pool.invokeAny(Arrays.asList(new Callable<String>() {
                                                        @Override
                                                        public String call() throws Exception {
                                                            log.info("begin");
                                                            Thread.sleep(1000);
                                                            return "1";
                                                        }
                                                    },
                    new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            log.info("begin");
                            Thread.sleep(800);
                            return "2";
                        }
                    },
                    () -> {
                        log.info("begin");
                        Thread.sleep(2000);
                        return "3";
                    }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        log.info(s);

    }
}
