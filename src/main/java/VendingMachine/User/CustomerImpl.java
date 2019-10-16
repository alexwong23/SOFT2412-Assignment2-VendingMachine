package VendingMachine.User;

import VendingMachine.model.Payment;
import VendingMachine.model.ShoppingCart;

public class CustomerImpl implements Customer{

    private ShoppingCart cart;

    public CustomerImpl(){
        cart = new ShoppingCart();
    }

    /**
     *
     * @return the shopping cart which manage the items customer plan to purchase
     */
    @Override
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     *
     * @param paymentAmount how much customer pay for the vending machine
     * @return the payment
     */
    @Override
    public Payment makePayment(double paymentAmount) {
        return new Payment(this, paymentAmount);
    }

    /**
     *
     * @return user's type, for customer, it will return CUSTOMER
     */
    @Override
    public UserType getType() {
        return UserType.CUSTOMER;
    }
}
