package chapterEgith;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 设置每周四18:00:00定时调度任务
 */
@Slf4j
public class Test228 {
    public static void main(String[] args) {
        LocalDateTime now= LocalDateTime.now();

        LocalDateTime setTime = LocalDateTime.now().withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        // 如果当前时间超过了星期四 那么就是设定下周四的定时任务
        if(now.compareTo(setTime)>0){
           setTime= setTime.plusWeeks(1);
        }
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(()->{
            log.info("niubi");
        }, Duration.between(now,setTime).toMillis(),1000*60*60*24*7, TimeUnit.MILLISECONDS);

    }
}
