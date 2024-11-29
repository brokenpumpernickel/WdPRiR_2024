import java.util.concurrent.atomic.AtomicInteger;

public class AtomicOperations {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.getAndIncrement();
    }
}
