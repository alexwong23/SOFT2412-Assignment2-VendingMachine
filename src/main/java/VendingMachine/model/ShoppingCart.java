package VendingMachine.model;

public class ShoppingCart {

    private Food[] cart;
    private int itemCount;      // total number of items in the cart
    private double totalPrice;  // total price of items in the cart
    private int capacity;       // current cart capacity

    public ShoppingCart()
    {

        capacity = 5;
        cart = new Food[capacity];
        itemCount = 0;
        totalPrice = 0.0;
    }

    public void addToCart(String itemName, double price, int quantity){

     //   Food temp = new Food(itemName, price, quantity);

        totalPrice = totalPrice + (price*quantity);

        itemCount += quantity;

        if(itemCount == capacity){

            capacity++;
        }



    }

}
