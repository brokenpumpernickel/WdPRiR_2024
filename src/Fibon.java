import java.util.LinkedList;
import java.util.concurrent.*;

public class Fibon {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = 0;
        long end = 0;

//        start = System.nanoTime();
//        for(int i = 0; i < 47; ++i)
//            System.out.println(i + " " + Helpers.fibon(i));
//        end = System.nanoTime();
//        System.out.println("Time (sequential): " + (end - start) / 1000000000.0);
//
//         System.out.println(Runtime.getRuntime().availableProcessors());
//
//        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        start = System.nanoTime();
//        for(int i = 0; i < 47; ++i) {
//            int j = i;
//            ex.execute(() -> System.out.println(j + " " + Helpers.fibon(j)));
//        }
//        ex.shutdown();
//        ex.awaitTermination(1, TimeUnit.DAYS);
//        end = System.nanoTime();
//        System.out.println("Time (pool - execute): " + (end - start) / 1000000000.0);

//        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        LinkedList<Future<String>> results = new LinkedList<>();
//        start = System.nanoTime();
//        for(int i = 0; i < 47; ++i) {
//            int j = i;
//            results.add(ex.submit(() -> j + " " + Helpers.fibon(j)));
//        }
//        for(var future : results)
//            System.out.println(future.get());
//        ex.shutdown();
//        ex.awaitTermination(1, TimeUnit.DAYS);
//        end = System.nanoTime();
//        System.out.println("Time (pool - execute): " + (end - start) / 1000000000.0);

        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService<String> cs = new ExecutorCompletionService<>(ex);
        start = System.nanoTime();
        for(int i = 0; i < 47; ++i) {
            int j = i;
            cs.submit(() -> j + " " + Helpers.fibon(j));
        }
        for(int i = 0; i < 47; ++i) {
            System.out.println(cs.take().get());
        }
        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.DAYS);
        end = System.nanoTime();
        System.out.println("Time (pool - execute): " + (end - start) / 1000000000.0);
    }

}
