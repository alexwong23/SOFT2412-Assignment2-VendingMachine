package VendingMachine.model;

import VendingMachine.config.VendingMachineConfig;


/* Vending machine for customers to interact with. Staff directly interact with Inventory. */
public class VendingMachine {
    Inventory inventory;
    Coffer coffer;
    Records records;

    public VendingMachine(VendingMachineConfig config) {
        inventory = new Inventory(config.getFoodConfigs());
        coffer = new Coffer(config.getCashConfigs());
        records = new Records();
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

    // SHOW DENOMINATION QUANTITY FOR TESTING PURPOSE
    public String cashToString(){
        String s = String.format("%-5s%-20s%-10s\n", "ID","Items","Type");
        s += "------------------------------------------------------\n";

        for (CofferDenomination denomination : coffer.getCofferDenominations()) {
            s += String.format("%s\n", denomination.getStaffDisplayString());
        }
        s += "======================================================\n";

        return s;
    }

    // FOR STAFF ONLY
    public String staffCashToString(){
        String s = String.format("%-5s%-20s%-10s%-10s%-10s\n", "ID","Items","Type","Value","Quantity");
        s += "------------------------------------------------------\n";

        for (CofferDenomination denomination : coffer.getCofferDenominations()) {
            s += String.format("%s\n", denomination.getStaffDisplayString());
        }
        s += "======================================================\n";

        return s;
    }

    // FOR STAFF ONLY
    public String recordsToString(){
        String s = String.format("%-20s%-10s%-10s%-10s\n", "Date","Item","Quantity","Type");
        s += "----------------------------------------------------------------\n";

        for (Record record : records.getRecords()) {
            s += String.format("%s\n", record.getDisplayString());
        }
        s += "================================================================\n";

        return s;
    }

    /* This should only be called by the StaffInterface class. User's in my opinion should only interact with this Vending machine instance */
    public Inventory getInventory() {
        return inventory;
    }
    public Coffer getCoffer() {
        return coffer;
    }
    public Records getRecords() {
        return records;
    }

}
