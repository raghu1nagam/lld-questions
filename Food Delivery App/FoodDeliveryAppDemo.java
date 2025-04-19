import User.PrimeUser;
import User.User;
import payment.PaymentMethod;
import payment.PaymentMode;

import java.util.ArrayList;
import java.util.List;

import static User.UserType.PRIME;

public class FoodDeliveryAppDemo {

    public static void main(String[] args) {

        FoodDeliveryApp instance = FoodDeliveryApp.getInstance();

        User user1 = instance.addUser("user1","567","123@gmail.com", PRIME);

        Restaurant dominos = instance.addRestaurant("dominos","1234");
        List<Item> dominosItems = List.of(new Item("paneer piza","120",true),new Item("chicken pizza","200",true));
        instance.addMenuItems(dominos,dominosItems);

        Restaurant kfc = instance.addRestaurant("kfc","2345");
        List<Item> kfcItems = List.of(new Item("fried chicken","210",true),new Item("peri peri chicken","240",true));
        instance.addMenuItems(kfc,kfcItems);

        instance.viewRestaurants();
        List<Item> items = instance.viewMenu(dominos);
        List<Item> itemsToBeOrdered = items.subList(0,1);


        DeliveryPartner deliveryPartner1 = instance.addDeliveryPartner("Suresh","890");
        DeliveryPartner deliveryPartner2 = instance.addDeliveryPartner("Ramesh","567");

        Order dominosOrder = instance.order(user1,dominos,itemsToBeOrdered, PaymentMethod.UPI);

        instance.orderPrepared(dominos,dominosOrder);
        instance.deliveryPartnerPickup(deliveryPartner1);
        instance.deliveryPartnerDelivered(deliveryPartner1);
        instance.rateDeliveryPartner(deliveryPartner1,4);
    }
}
