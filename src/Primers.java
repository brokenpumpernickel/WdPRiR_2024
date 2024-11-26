public class Primers {
    public static class Worker extends Thread {
        private Counter counter;

        public Worker(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            int number;
            int count = 0;
            while((number = counter.getAndIncrement()) < 1000000) {
                if(Helpers.isPrime(number));
                    System.out.println(number + " / " + getId());
                ++count;
            }

            System.out.println("Final count: " + count + " / " + getId());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        long start = System.nanoTime();

        Worker[] workers = new Worker[2];
        for(int i = 0; i < workers.length; ++i)
            workers[i] = new Worker(counter);

        for(int i = 0; i < workers.length; ++i) {
            workers[i].start();
        }

        for(int i = 0; i < workers.length; ++i) {
            workers[i].join();
        }

        long stop = System.nanoTime();

        System.out.println("Time: " + (stop - start) / 1000000000.0);
    }
}
