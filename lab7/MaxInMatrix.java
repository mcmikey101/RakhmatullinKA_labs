public class MaxInMatrix {
    private static class RowMax implements Runnable {
        private final int[] row;
        private int max;

        public RowMax(int[] row) {
            this.row = row;
        }

        public int getMax() {
            return max;
        }

        @Override
        public void run() {
            int m = row[0];
            for (int v : row) {
                if (v > m) {
                    m = v;
                }
            }
            max = m;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] matrix = {
                {1, 7, 3},
                {4, 2, 9},
                {6, 5, 8}
        };

        int n = matrix.length;
        RowMax[] tasks = new RowMax[n];
        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            tasks[i] = new RowMax(matrix[i]);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (int i = 0; i < n; i++) {
            threads[i].join();
        }

        int globalMax = tasks[0].getMax();
        for (int i = 1; i < n; i++) {
            int v = tasks[i].getMax();
            if (v > globalMax) {
                globalMax = v;
            }
        }

        System.out.println(globalMax);
    }
}
