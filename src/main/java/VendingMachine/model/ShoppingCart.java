package VendingMachine.model;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Food> cart = new ArrayList<Food>(); // actual list of food.
    private int cartSize;      // total number of items in the cart
    private double totalPrice;  // total price of items in the cart

    public ShoppingCart()
    {
        cartSize = 0;

        totalPrice = 0.0;
    }


    /**
     * Adds a Food item to the Cart.
     * @param e
     */
    public void addToCart(Food e)
    {

        cart.add(e);

        totalPrice = totalPrice + e.getPrice();

        cartSize++;

    }

    /**
     * Remove a Food item from the Cart.
     * @param e
     */
    public void removeFromCart(Food e)
    {

        cart.remove(e);

        totalPrice = totalPrice - e.getPrice();

        cartSize--;

    }




}
