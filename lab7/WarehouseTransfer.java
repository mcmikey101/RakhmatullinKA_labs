import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class WarehouseTransfer {
    private static final int LIMIT = 150;

    private static class Loader implements Runnable {
        private final List<Integer> weights;
        private final AtomicInteger currentLoad;
        private final CyclicBarrier barrier;

        public Loader(List<Integer> weights, AtomicInteger currentLoad, CyclicBarrier barrier) {
            this.weights = weights;
            this.currentLoad = currentLoad;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            for (int w : weights) {
                while (true) {
                    int prev = currentLoad.get();
                    if (prev + w > LIMIT) break;
                    if (currentLoad.compareAndSet(prev, prev + w)) break;
                }
                try {
                    barrier.await();
                } catch (Exception ignored) {
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> loader1 = Arrays.asList(20, 40, 30, 10, 25);
        List<Integer> loader2 = Arrays.asList(35, 15, 45, 20, 5);
        List<Integer> loader3 = Arrays.asList(25, 30, 10, 15, 20);

        AtomicInteger currentLoad = new AtomicInteger(0);

        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            currentLoad.set(0);
        });

        Thread t1 = new Thread(new Loader(loader1, currentLoad, barrier));
        Thread t2 = new Thread(new Loader(loader2, currentLoad, barrier));
        Thread t3 = new Thread(new Loader(loader3, currentLoad, barrier));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ignored) {
        }
    }
}
