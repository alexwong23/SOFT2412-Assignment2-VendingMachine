package VendingMachine.User;

public class Admin implements User {

    public Admin(){

    }

    public void restock(){

    }

    /**
     *
     * @return the user's type, for admin, it will return ADMIN
     */
    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }
}
