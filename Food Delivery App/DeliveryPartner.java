import java.util.ArrayList;
import java.util.List;

public class DeliveryPartner {
    private String name;
    private String phoneNumber;
    private boolean isAvailable;
    private Order currentOrder;
    private float rating;
    private List<Order> ordersServed;

    public boolean checkIsAvailable() {
        return this.isAvailable;
    }

    public void assignOrder(Order order) {
        System.out.println("Delivery Partner " +  this.name +" got assigned to" + order.getOrderId());
        this.currentOrder = order;
    }

    DeliveryPartner(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isAvailable = true;
        this.currentOrder = null;
        this.rating = 0;
        this.ordersServed = new ArrayList<>();
    }

    public void orderPickedUp() {
        System.out.println("Delivery Partner " +  this.name +" is on the way for order " + this.currentOrder.getOrderId());
        this.currentOrder.setOrderStatus(OrderStatus.ON_THE_WAY);
    }

    public void orderDelivered() {
        System.out.println("Delivery Partner " +  this.name +" has delivered the order " + this.currentOrder.getOrderId());
        this.currentOrder.setOrderStatus(OrderStatus.DELIVERED);
        this.ordersServed.add(this.currentOrder);
        this.isAvailable = false;
    }

    public void goOnline() {
        this.isAvailable = true;
    }

    public void goOffline() {
        this.isAvailable = false;
    }

    public void updateRating(int rating) {
        int numOfOrders = ordersServed.size();
        this.rating = (this.rating * (numOfOrders - 1) + rating)/(numOfOrders);
    }
}
