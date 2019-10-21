package VendingMachine.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Shopping Cart class, stores the customer's purchase selection.
 *
 */
public class ShoppingCart{

    private List<Food> cart; //actual list of food
    private double totalPrice;  // total price of items in the cart

    /**
     * ShoppingCart constructor
     */
    public ShoppingCart() {

        cart = new ArrayList<Food>();

        totalPrice = 0.0;
    }

    /**
     * Adds a Food item to the Cart.
     *
     * @param e, Adds the actual food item selected
     */
    public void addToCart(Food e) {

        if(e.getQuantity() <= 0 ){

            System.out.println("Sorry this item is currently out of stock.");

        }else {

            cart.add(e);

            totalPrice = totalPrice + e.getPrice();

        }

    }

    /**
     * Remove a Food item from the Cart.
     * @param e, Adds the actual food item selected
     */
    public void removeFromCart(Food e) {

        cart.remove(e.getId());

        totalPrice = totalPrice - e.getPrice();

    }

    public List<Food> getCart() {

        return cart;
    }

    public int getCartSize() {

        return cart.size();
    }

    public double getTotalPrice() {

        for(int i = 0; i < cart.size(); i++){

          totalPrice = cart.get(i).getPrice();
        }

        return totalPrice;
    }

    /**
     * Removes all items from the cart.
     * Sets the total price and cart size to 0.
     */

    public void resetCart() {

        this.cart.clear();

        this.totalPrice = 0;

    }

    /**
     * Simple toString method to print the current "state" of the cart as a string.
     * @return total price and how many items are in the cart.
     */
    @Override
    public String toString(){

        return getCart() +"Total Price: "+totalPrice+ "Cart size: "+ getCartSize();

    }

}

