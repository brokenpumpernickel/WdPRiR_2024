import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadersWritersProblem {
    public static class DummyResource {
        private int value = 0;

        public int getValue() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static DummyResource resource = new DummyResource();
    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static class Reader extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.readLock().lock();
                System.out.println("Reader: " + getId() + " " + resource.getValue());
                lock.readLock().unlock();
            }
        }
    }

    public static class Writer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.writeLock().lock();
                int value = resource.getValue();
                value += 1;
                resource.setValue(value);
                System.out.println("Writer: " + getId() + " " + value);
                lock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Reader().start();
        }
        for (int i = 0; i < 5; i++) {
            new Writer().start();
        }
    }
}
