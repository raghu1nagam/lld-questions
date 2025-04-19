package User;

public class UserTypeFactory {
    public static User getUserInstance(UserType userType) {
        if (userType == UserType.PRIME) {
            return new PrimeUser();
        }
        else{
            return new NormalUser();
        }
    }
}
