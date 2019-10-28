package VendingMachine.User;

public interface User {

    UserType getType();

    enum UserType{
        ADMIN, CUSTOMER
    }
}
