import java.util.LinkedList;

class Order {
    private String[] items;
    private String address;
    private double totalCost;

    public Order(String[] items, String address, double totalCost) {
        this.items = items;
        this.address = address;
        this.totalCost = totalCost;
    }

    public String[] getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Адрес: ").append(address).append("\n");
        sb.append("Товары: ");
        for (String item : items) {
            sb.append(item).append(" ");
        }
        sb.append("\nСтоимость: ").append(totalCost);
        return sb.toString();
    }
}

public class OrderHashTable {
    private static class Entry {
        int orderNumber;
        Order order;

        Entry(int orderNumber, Order order) {
            this.orderNumber = orderNumber;
            this.order = order;
        }
    }

    private LinkedList<Entry>[] table;
    private int capacity;
    private int size;

    public OrderHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
    }

    private int hash(int orderNumber) {
        return Math.abs(orderNumber % capacity);
    }

    public void put(int orderNumber, Order order) {
        int index = hash(orderNumber);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry entry : table[index]) {
            if (entry.orderNumber == orderNumber) {
                entry.order = order;
                return;
            }
        }

        table[index].add(new Entry(orderNumber, order));
        size++;
    }

    public Order get(int orderNumber) {
        int index = hash(orderNumber);
        if (table[index] == null) return null;

        for (Entry entry : table[index]) {
            if (entry.orderNumber == orderNumber) {
                return entry.order;
            }
        }
        return null;
    }

    public void remove(int orderNumber) {
        int index = hash(orderNumber);
        if (table[index] == null) return;

        for (Entry entry : table[index]) {
            if (entry.orderNumber == orderNumber) {
                table[index].remove(entry);
                size--;
                return;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        OrderHashTable orders = new OrderHashTable(10);

        orders.put(1001, new Order(new String[]{"Ноутбук", "Мышь"}, "Москва, Ленина 12", 85000));
        orders.put(1002, new Order(new String[]{"Телефон"}, "Санкт-Петербург, Невский 45", 60000));
        orders.put(1003, new Order(new String[]{"Наушники", "Чехол"}, "Казань, Баумана 7", 7000));

        System.out.println("Заказ 1002:");
        System.out.println(orders.get(1002));

        orders.remove(1002);

        System.out.println("\nПосле удаления заказа 1002:");
        System.out.println("Найден заказ 1002? " + (orders.get(1002) != null));
    }
}
