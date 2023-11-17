package chapterSix;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test189 {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        for(int a=0;a<100;a++){
            new Thread(()->{
                try {
                    System.out.println(simpleDateFormat.parse("2012-12-12"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
