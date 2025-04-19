package payment;

import static payment.PaymentStatus.SUCCESS;

public class CardPaymentMode implements PaymentMode{
    @Override
    public PaymentStatus pay(Long amount) {
        System.out.println("Connecting to the third party for card payment....");
        return SUCCESS;
    }
}
