package charterfour;

public class Test90 {
    public static  Object obj = new Object();
    public static void main(String[] args) {
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
