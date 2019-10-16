package VendingMachine.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Shopping Cart class, stores the customer's purchase selection.
 *
 */
public class ShoppingCart {

    private List<Food> cart; ; // actual list of food.
    private int cartSize;      // total number of items in the cart
    private double totalPrice;  // total price of items in the cart

    /**
     * ShoppingCart constructor
     */
    public ShoppingCart()
    {
        cart = new ArrayList<Food>();

        cartSize = 0;

        totalPrice = 0.0;
    }


    /**
     * Adds a Food item to the Cart.
     * @param e, the actual food item selected
     * @param quantity, the amount of that specific food item that was selected.
     */
    public void addToCart(Food e, int quantity) {

        if(quantity <= 0){

            System.out.println("Invalid amount inputted.");

        } else {

            cart.add(e);

            totalPrice = totalPrice + (e.getPrice() * quantity);

            cartSize = cartSize + quantity;

        }

    }

    /**
     * Remove a Food item from the Cart.
     * @param e, the actual food item selected
     * @param quantity, the amount of that specific food item that was selected.
     */
    public void removeFromCart(Food e, int quantity) {

        if(cartSize <= 0){

            System.out.println("Trying to remove from an empty cart.");

        } else if(quantity <= 0) {

            System.out.println("Invalid amount inputted.");

        } else {

            cart.remove(e);

            totalPrice = totalPrice - (e.getPrice() * quantity);

            cartSize = cartSize - quantity;

        }

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
     *  Removes all items from the cart.
     *  Sets the total price and cart size to 0.
     */

    public void resetCart(){

        this.cart.clear();

        this.totalPrice = 0;

        this.cartSize = 0;

    }

}
