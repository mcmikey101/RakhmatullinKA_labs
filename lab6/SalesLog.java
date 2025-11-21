import java.util.HashMap;
import java.util.Map;

public class SalesLog {

    private final Map<String, Integer> sales = new HashMap<>();

    public void add(String item, int count) {
        sales.put(item, sales.getOrDefault(item, 0) + count);
    }

    public void printAll() {
        for (Map.Entry<String, Integer> e : sales.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    public int total() {
        int sum = 0;
        for (int v : sales.values()) {
            sum += v;
        }
        return sum;
    }

    public String mostPopular() {
        String top = null;
        int max = 0;
        for (Map.Entry<String, Integer> e : sales.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                top = e.getKey();
            }
        }
        return top;
    }

    public static void main(String[] args) {
        SalesLog log = new SalesLog();
        log.add("Bread", 5);
        log.add("Milk", 3);
        log.add("Bread", 2);
        log.add("Tea", 4);

        log.printAll();
        System.out.println("Total: " + log.total());
        System.out.println("Most popular: " + log.mostPopular());
    }
}
