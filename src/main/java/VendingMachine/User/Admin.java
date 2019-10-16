package VendingMachine.User;

public class Admin implements User {

    public Admin(){

    }

    public void restock(){

    }

    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }
}
