import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier001 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[6];

        CyclicBarrier cb = new CyclicBarrier(threads.length, () -> System.out.println("Section finished!"));

        for(int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    Random random = new Random();
                    try {
                        while (true) {
                            sleep(random.nextInt(2000));
                            System.out.println("Job A finished - " + getId());

                            cb.await();

                            sleep(random.nextInt(2000));
                            System.out.println("Job B finished - " + getId());

                            cb.await();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }

        for (int i = 0; i < threads.length; ++i) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; ++i) {
            threads[i].join();
        }
    }
}
