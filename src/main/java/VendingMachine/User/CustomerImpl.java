package VendingMachine.User;

import VendingMachine.model.ShoppingCart;

public class CustomerImpl implements Customer{

    private ShoppingCart cart;

    public CustomerImpl(){
        cart = new ShoppingCart();
    }

    /**
     *Get the shopping cart which contain all items customer plan to purchase
     * @return the shopping cart
     */
    @Override
    public ShoppingCart getCart() {
        return cart;
    }

//    /**
//     *It creates a payment
//     * @param paymentAmount how much customer pay for the vending machine
//     * @return the payment
//     */
//    @Override
//    public Payment getPayment(double paymentAmount) {
//        return new Payment(this, paymentAmount);
//    }

    /**
     *For customer class, it will return CUSTOMER
     * @return user's type
     */
    @Override
    public UserType getType() {
        return UserType.CUSTOMER;
    }
}
