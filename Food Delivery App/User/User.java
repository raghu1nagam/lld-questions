package User;

public abstract class User {
    String name;
    String phoneNumber;
    String email;
    public abstract void register();
    public abstract Long getDeliveryCharges();

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
