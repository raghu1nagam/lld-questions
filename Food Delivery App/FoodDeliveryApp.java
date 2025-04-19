import User.User;
import User.UserType;
import User.UserTypeFactory;
import payment.PaymentMethod;
import payment.PaymentStatus;

import java.util.ArrayList;
import java.util.List;


public class FoodDeliveryApp {

    private static volatile FoodDeliveryApp foodDeliveryAppInstance = null;

    private FoodDeliveryApp() {

    }

    private List<User> users = new ArrayList<>();
    private List<DeliveryPartner> deliveryPartners = new ArrayList<>();

    private List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant addRestaurant(String name, String phoneNumber) {
        Restaurant restaurant = new Restaurant(name,phoneNumber);
        restaurants.add(restaurant);
        return restaurant;
    }

    public DeliveryPartner addDeliveryPartner(String name, String phoneNumber) {
        DeliveryPartner deliveryPartner = new DeliveryPartner(name,phoneNumber);
        deliveryPartners.add(deliveryPartner);
        return deliveryPartner;
    }

    public void addMenuItems(Restaurant restaurant, List<Item> items) {
        restaurant.addMenu(items);
    }

    public void viewRestaurants() {
        System.out.println("Restaurants ::");
        restaurants.forEach(restaurant -> {
            System.out.println(restaurant.getName());
        });
    }

    public List<Item> viewMenu(Restaurant restaurant) {
        return restaurant.viewMenu();
    }

    public static synchronized FoodDeliveryApp getInstance() {
        if (foodDeliveryAppInstance == null) {
            synchronized (FoodDeliveryApp.class) {
                if (foodDeliveryAppInstance == null) {
                    foodDeliveryAppInstance = new FoodDeliveryApp();
                    return foodDeliveryAppInstance;
                }
            }
        }
        return  foodDeliveryAppInstance;
    }

    public User addUser(String name, String phoneNumber, String email, UserType userType) {
        User user = UserTypeFactory.getUserInstance(userType);
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.register();
        users.add(user);
        return user;
    }

    public Order order(User user, Restaurant restaurant, List<Item> items, PaymentMethod paymentMethod) {
        Order order = new Order(user,items,restaurant);
        Long amount = 0L;
        for (Item item : items) {
            amount += (item.getPrice());
        }
        amount += user.getDeliveryCharges();
        PaymentStatus paymentStatus = order.pay(paymentMethod,amount);
        order.setPaymentStatus(paymentStatus);
        if (paymentStatus == PaymentStatus.SUCCESS) {
            order.setOrderStatus(OrderStatus.PLACED);
            restaurant.recieveOrder(order);
            checkForDeliveryPartner(order);
        }
        else {
            System.out.println("Payment was not Successful, please retry again");
        }
        return order;
    }

    private void checkForDeliveryPartner(Order order) {
        while (orderEligibleForDeliveryPartner(order.getOrderStatus())) {
           System.out.println("Checking for Available delivery Partners...");

           for (DeliveryPartner deliveryPartner : deliveryPartners) {
               if (deliveryPartner.checkIsAvailable()) {
                   order.setOrderStatus(OrderStatus.PARTNER_ASSIGNED_PREPARING);
                   deliveryPartner.assignOrder(order);
                   break;
               }
           }
           if (order.getOrderStatus().ordinal() < OrderStatus.PARTNER_ASSIGNED_PREPARING.ordinal()) {
               System.out.println("Seems like our delivery partners are busy... will try again");
           }
        }
    }

    private boolean orderEligibleForDeliveryPartner(OrderStatus orderStatus) {
        return  (orderStatus.ordinal() >= OrderStatus.ACCEPTED.ordinal() && orderStatus.ordinal() < OrderStatus.PARTNER_ASSIGNED_PREPARING.ordinal());
    }

    public void orderPrepared(Restaurant restaurant, Order order) {
        restaurant.orderPrepared(order);
    }

    public void deliveryPartnerPickup(DeliveryPartner deliveryPartner) {
        deliveryPartner.orderPickedUp();
    }

    public void deliveryPartnerDelivered(DeliveryPartner deliveryPartner) {
        deliveryPartner.orderDelivered();
    }

    public void rateDeliveryPartner(DeliveryPartner deliveryPartner, int rating) {
        deliveryPartner.updateRating(rating);
    }
}
