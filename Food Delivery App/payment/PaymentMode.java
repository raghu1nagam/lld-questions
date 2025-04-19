package payment;

public interface PaymentMode {
    PaymentStatus pay(Long amount);
}
