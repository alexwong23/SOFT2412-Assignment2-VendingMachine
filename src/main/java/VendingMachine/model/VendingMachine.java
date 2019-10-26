package VendingMachine.model;

import VendingMachine.config.VendingMachineConfig;

import java.util.ArrayList;

/* Vending machine for customers to interact with. Staff directly interact with Inventory. */
public class VendingMachine {

    Inventory inventory;
    Coffer coffer;

    public VendingMachine(VendingMachineConfig config) {
        inventory = new Inventory(config.getFoodConfigs());
        coffer = new Coffer(config.getCashConfigs());
    }

    public String foodToString(){
        String s = String.format("%-5s%-20s%-10s%-10s%-10s\n", "ID","Items","Type","Price","Quantity");
        s += "------------------------------------------------------\n";

        for (InventoryItem item : inventory.getInventoryItems()) {
            s += String.format("%s\n", item.getDisplayString());
        }
        s += "======================================================\n";

        return s;
    }

    // FOR STAFF ONLY
    public String cashToString(){
        String s = String.format("%-5s%-20s%-10s%-10s%-10s\n", "ID","Items","Type","Value","Quantity");
        s += "------------------------------------------------------\n";

        for (CofferDenomination denomination : coffer.getCofferDenominations()) {
            s += String.format("%s\n", denomination.getDisplayString());
        }
        s += "======================================================\n";

        return s;
    }

    /* This should only be called by the StaffInterface class. User's in my opinion should only interact with this Vending machine instance */
    public Inventory getInventory() {
        return inventory;
    }
    public Coffer getCoffer() {
        return coffer;
    }

}
