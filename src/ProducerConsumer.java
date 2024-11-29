import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
    public static class Producer extends Thread {
        private BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void run() {
            Random random = new Random();
            for (int i = 0; true; i++) {
                try {
                    sleep(random.nextInt(6000));
                    System.out.println("Produced: " + i + " by " + getId());
                    queue.put(getId() + " - " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class Consumer extends Thread {
        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void run() {
            Random random = new Random();
            while (true) {
                try {
                    String result = queue.take();
                    sleep(random.nextInt(6000));
                    System.out.println("Consumed: " + result + "; by " + getId());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Producer[] producers = new Producer[6];
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Producer(queue);
        }

        Consumer[] consumers = new Consumer[6];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(queue);
        }

        for (Producer producer : producers) {
            producer.setDaemon(true);
            producer.start();
        }

        for (Consumer consumer : consumers) {
            consumer.setDaemon(true);
            consumer.start();
        }

        for (Consumer consumer : consumers) {
            consumer.join();
        }
    }
}
