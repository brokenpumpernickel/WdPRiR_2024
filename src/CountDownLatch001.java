import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatch001 {
    public static void main(String[] args) throws InterruptedException {
        long start = 0;
        long end = 0;

        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        start = System.nanoTime();

        CountDownLatch cdl = new CountDownLatch(47);
        for(int i = 0; i < 47; ++i) {
            int j = i;
            ex.execute(() -> {
                System.out.println(j + " " + Helpers.fibon(j));
                cdl.countDown();
            });
        }
        cdl.await();
        System.out.println("Everything is ready!");

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.DAYS);
        end = System.nanoTime();
        System.out.println("Time (pool - execute): " + (end - start) / 1000000000.0);
    }
}
