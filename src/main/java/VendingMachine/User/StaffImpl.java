package VendingMachine.User;

import VendingMachine.model.Inventory;

public class StaffImpl implements Staff {
    @Override
    public Inventory fill() {
        return null;
    }
    /**
     *For customer class, it will return CUSTOMER
     * @return user's type
     */
    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }
}
