package VendingMachine.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Shopping Cart class, stores the customer's purchase selection.
 *
 */
public class ShoppingCart{

    private List<InventoryItem> cart; //actual list of food

    /**
     * ShoppingCart constructor
     */
    public ShoppingCart() {

        cart = new ArrayList<InventoryItem>();

    }

    /**
     * Adds a Food item to the Cart.
     *
     * @param e, Adds the actual food item selected
     */
    public void addToCart(InventoryItem e, int quantity) {

        if(quantity <= 0 ) {
            System.out.println("Sorry, invalid quantity provided.");
        } else if(e.getQuantity() <= 0 ) {
            System.out.println("Sorry, this item is currently out of stock.");
        } else if(e.getQuantity() < quantity) {
            System.out.println("Sorry, we do not have enough stock to accommodate your request.");
        } else {
            e.reduceQuantity(quantity); // reduce original quantity
            if(cart.contains(e)) {
                // bad coding try to identify duplicate items here
                cart.get(cart.indexOf(e)).addQuantity(quantity); // close Inventory Item and add quantity
            } else {
                cart.add(e.clone(quantity)); // close Inventory Item and add quantity
            }

        }
    }

    /**
     * Remove a Food item from the Cart.
     * @param e, Adds the actual food item selected
     */
    public void removeFromCart(InventoryItem e, int qua) {

        for(InventoryItem item: cart){
            if(item.getFood().getId()==e.getFood().getId()){
                int newQua = item.getQuantity()-qua;
                if(newQua==0) {
                    cart.remove(item);
                }else{
                    item.setQuantity(newQua);
                }
                break;
            }
        }

    }

    public List<InventoryItem> getCart() {

        return cart;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for(int i = 0; i < cart.size(); i++){
            totalQuantity += cart.get(i).getQuantity();
        }
        return totalQuantity;
    }

    public double getTotalPrice() {
        double totalPrice=0;

        for(int i = 0; i < cart.size(); i++){

          totalPrice += cart.get(i).getFood().getPrice()*cart.get(i).getQuantity();
        }

        return totalPrice;
    }

    /**
     * Removes all items from the cart.
     * Sets the total price and cart size to 0.
     */

    public void resetCart() {

        this.cart.clear();

    }

    /**
     * Simple toString method to print the current "state" of the cart as a string.
     * @return total price and how many items are in the cart.
     */
    @Override
    public String toString(){
        String s = "";
        s+="------------------Shopping Cart------------------\n";
        s+=String.format("%-5s%-20s%-10s%-10s%-10s\n","ID","Items","Type","Price","Quantity");
        for(InventoryItem item: cart){
            s+=item.getDisplayString()+"\n";
        }
//        s+="\nTotal Quantity: "+getTotalQuantity()+"\n"; // dont need this
        s+="Total Price: $ "+getTotalPrice()+"\n";
        return s;
    }
}

