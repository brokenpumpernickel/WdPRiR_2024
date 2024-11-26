public class Threads001 {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello multithreaded world from " + getId());
        }
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello multithreaded world from runnable " + Thread.currentThread().getId());
        }
    }

    public static void runMe() {
        System.out.println("Hello multithreaded world from function " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello from main");

        Thread t2 = new Thread(new MyRunnable());
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread t3 = new Thread(() -> System.out.println("Hello from lambda!"));
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread t4 = new Thread(Threads001::runMe);
        t4.start();
        try {
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread t5 = new Thread() {
            @Override
            public void run() {
                super.run();
            }
        };
    }
}
