import java.util.HashMap;

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

public class OrderHashMap {
    private HashMap<Integer, Order> orders;

    public OrderHashMap() {
        orders = new HashMap<>();
    }

    public void addOrder(int orderNumber, Order order) {
        orders.put(orderNumber, order);
    }

    public Order getOrder(int orderNumber) {
        return orders.get(orderNumber);
    }

    public void removeOrder(int orderNumber) {
        orders.remove(orderNumber);
    }

    public int size() {
        return orders.size();
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public static void main(String[] args) {
        OrderHashMap manager = new OrderHashMap();

        manager.addOrder(1, new Order(new String[]{"Ноутбук", "Мышь"}, "Москва", 85000));
        manager.addOrder(2, new Order(new String[]{"Телефон"}, "Набережные Челны", 60000));
        manager.addOrder(3, new Order(new String[]{"Наушники", "Чехол"}, "Казань", 7000));

        System.out.println("Заказ 2:");
        System.out.println(manager.getOrder(2));

        manager.removeOrder(2);

        System.out.println("\nПосле удаления заказа 2:");
        System.out.println("Заказ 2: " + manager.getOrder(2));
    }
}
