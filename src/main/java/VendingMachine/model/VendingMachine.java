package VendingMachine.model;

import VendingMachine.config.VendingMachineConfig;

import java.util.ArrayList;

/* Vending machine for customers to interact with. Staff directly interact with Inventory. */
public class VendingMachine {

    Inventory inventory;

    public VendingMachine(VendingMachineConfig config) {
        inventory = new Inventory(config.getFoodConfigs());
    }

    public ArrayList<Food> getAllFood() {
        return inventory.getAllFood();
    }

    /* This should only be called by the StaffInterface class. User's in my opinion should only interact with this Vending machine instance */
    public Inventory getInventory() {
        return inventory;
    }
}