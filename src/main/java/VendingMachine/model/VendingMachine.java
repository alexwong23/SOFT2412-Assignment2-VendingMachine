package VendingMachine.model;

import VendingMachine.config.VendingMachineConfig;

import java.util.ArrayList;

/* Vending machine for customers to interact with. Staff directly interact with Inventory. */
public class VendingMachine {

    Inventory inventory;

    public VendingMachine(VendingMachineConfig config) {

        inventory = new Inventory(/* Pass in food items here */);
    }
}
