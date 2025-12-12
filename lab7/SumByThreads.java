public class SumByThreads {
    private static class PartialSum implements Runnable {
        private final int[] arr;
        private final int start;
        private final int end;
        private long result;

        public PartialSum(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        public long getResult() {
            return result;
        }

        @Override
        public void run() {
            long s = 0;
            for (int i = start; i < end; i++) {
                s += arr[i];
            }
            result = s;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] data = new int[1000000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        int mid = data.length / 2;

        PartialSum part1 = new PartialSum(data, 0, mid);
        PartialSum part2 = new PartialSum(data, mid, data.length);

        Thread t1 = new Thread(part1);
        Thread t2 = new Thread(part2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long total = part1.getResult() + part2.getResult();
        System.out.println(total);
    }
}
