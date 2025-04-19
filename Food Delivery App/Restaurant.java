import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String name;
    private String phoneNumber;
    private Menu menu;
    private List<Order> orders;

    Restaurant(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.orders = new ArrayList<>();
    }
    public String getName() {
        return this.name;
    }

    public void addMenu(List<Item> items) {
        if (this.menu == null) {
            this.menu = new Menu();
        }
        this.menu.addItems(items);
    }

    public List<Item> viewMenu() {
        System.out.println(this.name);
        return this.menu.viewMenu();
    }

    public Order recieveOrder(Order order) {
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setOrderStatus(OrderStatus.PREPARING);
        orders.add(order);
        return order;
    }

    public void orderPrepared(Order order) {
        order.setOrderStatus(OrderStatus.PREPARED);
    }

}
