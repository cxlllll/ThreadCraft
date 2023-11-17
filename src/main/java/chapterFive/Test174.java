package chapterFive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Test174 {
    public static void main(String[] args) throws Exception {
        AtomicIntegerArray arrat = new AtomicIntegerArray(10);
        for (int a1 = 0; a1 < 5; a1++) {
            demo(() -> new int[10], (array) -> array.length, (a, b) -> a[b]++, array -> System.out.println(Arrays.toString(array)));
        }
        for (int a1 = 0; a1 < 5; a1++) {
            demo(() -> new AtomicIntegerArray(10), (array) -> array.length(), (a, b) -> a.getAndIncrement(b), array -> System.out.println(array));
        }

    }

    /**
     * @param supplier   没有参数，输出一个结果
     * @param function   一个参数，输出一个结果
     * @param biConsumer 两个参数，没有输出结果
     * @param consumer   一个参数，没有输出结果
     * @param <T>
     */
    private static <T> void demo(Supplier<T> supplier, Function<T, Integer> function, BiConsumer<T, Integer> biConsumer, Consumer<T> consumer) throws Exception {
        List<Thread> list = new ArrayList<>();
        T t = supplier.get(); // 输入一个数组
        int length = function.apply(t); // 获取数组长度
        for (int i = 0; i < length; i++) {
            list.add(new Thread(() -> {
                for (int a = 0; a < 10000; a++) {
                    biConsumer.accept(t, a % length);
                }
            }));
        }
        list.forEach(Thread::start);
        for (Thread thread : list) {
            thread.join();
        }
        consumer.accept(t);
    }
}
