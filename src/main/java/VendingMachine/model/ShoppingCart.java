package VendingMachine.model;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Food> cart = new ArrayList<Food>(); // actual list of food.
    private int itemCount;      // total number of items in the cart
    private double totalPrice;  // total price of items in the cart

    public ShoppingCart()
    {
        itemCount = 0;

        totalPrice = 0.0;
    }

    public void addToCart(Food e)
    {

        cart.add(e);

        totalPrice = totalPrice + e.getPrice();

        itemCount++;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

}
