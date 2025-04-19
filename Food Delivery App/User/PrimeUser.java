package User;

public class PrimeUser extends User{
    @Override
    public void register() {
        System.out.println("Registering Prime user...." + this.name);
    }

    @Override
    public Long getDeliveryCharges() {
        return 10L;
    }
}
