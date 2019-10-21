package VendingMachine.User;

public class Admin implements User {

    public Admin(){
    	
    }

    /**
     *  For Admin, it will return AMIN
     * @return the user's type
     */
    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }
}
