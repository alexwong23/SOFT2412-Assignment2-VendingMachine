package VendingMachine.User;

import VendingMachine.model.ShoppingCart;

public interface Customer extends User{
    ShoppingCart getCart();
}
