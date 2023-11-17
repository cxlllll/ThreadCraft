package charterfour;



import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 保护性暂停实现
 */
@Slf4j
public class Test98 {
    public static void main(String[] args) {
        Guarded guarded = new Guarded();
        new Thread(){
            @Override
            public void run() {
                guarded.getObj(5000);
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    HttpURLConnection urlConnection =(HttpURLConnection) new URL("https://www.baidu.com/").openConnection();
                    List<String> list = new ArrayList<>();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
                    String line ;
                    while((line= reader.readLine())!=null){
                        list.add(line);
                    }
                    guarded.completed(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
@Slf4j
class Guarded{


    private Object object;

    /**
     * 增加等待时间 n秒
     */
    public Object getObj(long n){
        synchronized (this){
            long begin = System.currentTimeMillis();
            while(object==null){
                long experienceTime = 0;//经历的时间
                if(experienceTime > n){
                    break;// 如果大于了这个时间 那么跳出循环
                }
                try {
                    this.wait(n-experienceTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                experienceTime = System.currentTimeMillis() - begin;
            }
            // 不为空就执行代码
            return object;
        } }

    public void completed(Object obj){
        synchronized (this){
            this.object = obj;
            System.out.println("填充完毕");
            this.notifyAll();
        }
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}