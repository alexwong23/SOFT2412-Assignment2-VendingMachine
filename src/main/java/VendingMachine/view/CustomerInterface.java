package VendingMachine.view;

import VendingMachine.model.Food;
import VendingMachine.model.VendingMachine;

public class CustomerInterface implements CommandLineInterface {
    public CustomerInterface(VendingMachine vendingMachine) {
        System.out.println("Welcome to vending machine!");
        for (Food item : vendingMachine.getAllFood()) {
            System.out.println(item.getDisplayString());
        }

    }
}