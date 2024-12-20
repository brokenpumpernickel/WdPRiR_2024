import java.util.concurrent.atomic.AtomicBoolean;

public class TestAndSetLock implements PoorLock {
    private AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        while(state.getAndSet(true));
    }

    @Override
    public void unlock() {
        state.set(false);
    }
}
