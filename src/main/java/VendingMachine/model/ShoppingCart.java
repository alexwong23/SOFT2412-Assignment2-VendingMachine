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

    public List<InventoryItem> getCart() { return cart; }

    public void resetCart() { this.cart.clear(); }

    public InventoryItem getInventoryItemByFoodId(int foodId) {
        for(InventoryItem item:this.cart){
            if(item.getFood().getId() == foodId){
                return item;
            }
        }
        return null;
    }

    /**
     * Adds a Food item to the Cart.
     *
     * @param e, Adds the actual food item selected
     */
    public void addToCart(InventoryItem e, int quantity) {
        if(e == null){
            System.out.println("Sorry, invalid ID provided.");
        }else if(quantity <= 0) {
            System.out.println("Sorry, invalid quantity provided.");
        } else if(e.getQuantity() <= 0) {
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
            System.out.println("Add successful.");
        }
    }

    /** ;
     * Remove a Food item from the Cart.
     * @param e, Adds the actual food item selected
     */
    public void removeFromCart(InventoryItem e, int quantity) {
        if(e == null) {
            System.out.println("Sorry, invalid ID provided.");
        } else if(quantity <= 0) {
            System.out.println("Sorry, invalid quantity provided.");
        } else if(e.getQuantity() < quantity) {
            System.out.println("Sorry, not enough in cart.");
        } else {
            e.reduceQuantity(quantity);
            if(e.getQuantity()==0) {
                cart.remove(e);
            }
            System.out.println("Delete successful.");
        }
    }

//    public int getTotalQuantity() {
//        int totalQuantity = 0;
//        for(int i = 0; i < cart.size(); i++){
//            totalQuantity += cart.get(i).getQuantity();
//        }
//        return totalQuantity;
//    }

    public double getTotalPrice() {
        double totalPrice=0;
        for (InventoryItem inventoryItem : cart) {
            totalPrice += inventoryItem.getFood().getPrice() * inventoryItem.getQuantity();
        }
        return totalPrice;
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
        s+="Total Price: $ "+getTotalPrice()+"\n";
        return s;
    }
}

