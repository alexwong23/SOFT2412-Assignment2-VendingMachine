package VendingMachine.model;

import VendingMachine.config.VendingMachineConfig;

import java.util.ArrayList;
import java.util.HashMap;

/* Vending machine for customers to interact with. Staff directly interact with Inventory. */
public class VendingMachine {
    Money money;
    Inventory inventory;

    public VendingMachine(VendingMachineConfig config) {
        inventory = new Inventory(config.getFoodConfigs());
        money = new Money();
    }

    public double totalMoney(){
        return money.totalMoney();
    }

    public String toString(){
        String s="";
        s += "===========Welcome to vending machine!================\n";
        s += String.format("%-5s%-20s%-10s%-10s%-10s\n", "ID","Items","Type","Price","Quantity");
        s += "------------------------------------------------------\n";

        for (InventoryItem item : inventory.getInventoryItems()) {
            s += String.format("%s\n", item.getDisplayString());
        }
        s += "======================================================\n";

        return s;
    }

    /* This should only be called by the StaffInterface class. User's in my opinion should only interact with this Vending machine instance */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @param foodId  The id of the food item
     * @param quantity How many food items the customer wants
     */
//    public Food retrieveInventoryItemForCustomer(int foodId, int quantity) {
//        int result = inventory.removeFoodFromInventory(foodId, quantity);
//        if (result == 0) {
//            return inventory.getFoodById(foodId);
//        }
//        return null;
//    }

}
