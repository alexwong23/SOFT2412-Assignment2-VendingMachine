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

    /**
     * ShoppingCart constructor
     */
    public ShoppingCart() {

        cart = new ArrayList<Food>();

    }

    /**
     * Adds a Food item to the Cart.
     *
     * @param e, Adds the actual food item selected
     */
    public void addToCart(Food e) {

        //if(e.getQuantity() <= 0 ){

        //    System.out.println("Sorry this item is currently out of stock.");

        //}else {

            cart.add(e);

        //}

    }

    /**
     * Remove a Food item from the Cart.
     * @param e, Adds the actual food item selected
     */
    public void removeFromCart(Food e, int qua) {

        for(Food food: cart){
            if(food.getId()==e.getId()){
                int newQua = food.getQuantity()-qua;
                if(newQua==0) {
                    cart.remove(food);
                }else{
                    food.setQuantity(newQua);
                }
                break;
            }
        }

    }

    public List<Food> getCart() {

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

          totalPrice += cart.get(i).getPrice()*cart.get(i).getQuantity();
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
        for(Food food: cart){
            s+=food.getDisplayString()+"\n";
        }
        s+="\nTotal Quantity: "+getTotalQuantity()+"\n";
        s+="Total Price: $ "+getTotalPrice()+"\n";
        return s;
    }
}

