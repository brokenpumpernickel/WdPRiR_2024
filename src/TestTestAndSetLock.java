import java.util.concurrent.atomic.AtomicBoolean;

public class TestTestAndSetLock implements PoorLock {
    private AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        while(true) {
            while(state.get());
            if(!state.getAndSet(true)) {
                return;
            }
        }
    }

    @Override
    public void unlock() {
        state.set(false);
    }
}
