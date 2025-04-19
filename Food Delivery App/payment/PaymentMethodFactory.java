package payment;

public class PaymentMethodFactory {

    public static PaymentMode getPaymentInstance(PaymentMethod paymentMethod) {
        if (paymentMethod == PaymentMethod.UPI) {
            return new UpiPaymentMode();
        }
        else {
            return new CardPaymentMode();
        }
    }
}
