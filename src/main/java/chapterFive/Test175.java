package chapterFive;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 使用字段更新器必须配合volatile使用 不然会报错
 */
public class Test175 {
    public static void main(String[] args) throws InterruptedException {
        Student stu  = new Student(null);
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Student.class,String.class,"name");
        Thread thread = new Thread(() -> {
            while (true) {
                boolean flag = updater.compareAndSet(stu, null, "销量");
                if(flag){
                    break;
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            while (true) {
                boolean flag = updater.compareAndSet(stu, "销量", "销量2");
                if (flag){
                    break;
                }
            }
        });
        thread.start();
        thread1.start();
        Thread.sleep(10);
        System.out.println(stu);
    }
}
class Student{
    volatile  String name;
    public Student(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
