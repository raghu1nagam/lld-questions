package payment;

import static payment.PaymentStatus.SUCCESS;

public class UpiPaymentMode implements PaymentMode{
    @Override
    public PaymentStatus pay(Long amount) {
        System.out.println("Connecting to a 3rd party application for upi...");
        return SUCCESS;
    }
}
