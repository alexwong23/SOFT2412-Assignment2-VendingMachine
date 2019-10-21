package VendingMachine.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Shopping Cart class, stores the customer's purchase selection.
 *
 */
public class ShoppingCart {

    private List<Food> cart; //actual list of food
    private int cartSize;      // total number of items in the cart
    private double totalPrice;  // total price of items in the cart

    /**
     * ShoppingCart constructor
     */
    public ShoppingCart() {

        cart = new ArrayList<Food>();

        cartSize = 0;

        totalPrice = 0.0;
    }

    /**
     * Adds a Food item to the Cart.
     *
     * @param e, Adds the actual food item selected
     */
    public void addToCart(Food e) {

            cart.add(e);

            totalPrice = totalPrice + e.getPrice();

            cartSize = cartSize++;

    }

    /**
     * Remove a Food item from the Cart.
     * @param e, Adds the actual food item selected
     */
    public void removeFromCart(Food e) {

        cart.remove(e);

        totalPrice = totalPrice - e.getPrice();

        cartSize--;

    }

    public List<Food> getCart() {

        return cart;
    }

    public int getCartSize() {

        return cartSize;
    }

    public double getTotalPrice() {

        return totalPrice;
    }

    /**
     * Removes all items from the cart.
     * Sets the total price and cart size to 0.
     */

    public void resetCart() {

        this.cart.clear();

        this.totalPrice = 0;

        this.cartSize = 0;

    }

    /**
     * Simple toString method to print the current "state" of the cart as a string.
     * @return total price and how many items are in the cart.
     */

    public String toString(){

        return "Total Price: "+totalPrice+ "Cart size: "+ cartSize;

    }


}
