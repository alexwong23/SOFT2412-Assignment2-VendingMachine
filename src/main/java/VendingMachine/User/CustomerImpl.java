package VendingMachine.User;

import VendingMachine.model.ShoppingCart;

public class CustomerImpl implements Customer{
    private ShoppingCart cart;

    public CustomerImpl(){
        cart = new ShoppingCart();
    }

    @Override
    public UserType getType() {
        return UserType.CUSTOMER;
    }

    @Override
    public ShoppingCart getCart() {
        return cart;
    }
}
