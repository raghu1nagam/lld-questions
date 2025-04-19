import User.User;
import payment.PaymentMethod;
import payment.PaymentMethodFactory;
import payment.PaymentMode;
import payment.PaymentStatus;

import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private User user;
    private List<Item> items;
    private OrderStatus orderStatus;
    private Restaurant restaurant;
    private PaymentStatus paymentStatus;

    Order(User user, List<Item> items,Restaurant restaurant){
        this.orderId = UUID.randomUUID().toString();
        this.user = user;
        this.items = items;
        this.orderStatus = OrderStatus.NOT_PAID;
        this.restaurant = restaurant;
        this.paymentStatus = PaymentStatus.NOT_PAID;
    }

    public PaymentStatus pay(PaymentMethod paymentMethod, Long amount) {
        PaymentMode paymentMode = PaymentMethodFactory.getPaymentInstance(paymentMethod);
        return paymentMode.pay(amount);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public String getOrderId() {
        return this.orderId;
    }
}
