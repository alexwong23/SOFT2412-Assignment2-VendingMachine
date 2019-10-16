package VendingMachine.User;

import VendingMachine.model.Payment;
import VendingMachine.model.ShoppingCart;

public interface Customer extends User{
    ShoppingCart getCart();
    Payment makePayment(double paymentAmount);
}
