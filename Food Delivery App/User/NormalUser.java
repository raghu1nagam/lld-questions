package User;

public class NormalUser extends User{
    @Override
    public void register() {
        System.out.println("Registering Normal User.....");
    }

    @Override
    public Long getDeliveryCharges() {
        return 20L;
    }
}
