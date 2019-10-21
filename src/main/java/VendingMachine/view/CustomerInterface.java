package VendingMachine.view;

import VendingMachine.model.Food;
import VendingMachine.model.VendingMachine;

public class CustomerInterface implements CommandLineInterface {
    private VendingMachine vd;

    public CustomerInterface(VendingMachine vendingMachine) {
        this.vd = vendingMachine;
        System.out.println("=========Welcome to vending machine!=========");
        printFoodList();

        // Code that reads user inputs goes here
        // Code that outputs data to users goes here
    }

    public void printFoodList(){
        System.out.printf("%-20s%-10s%-10s%-10s\n","Items","Type","Price","Quantity");
        System.out.println("---------------------------------------------");
        for (Food item : this.vd.getAllFood()) {
            System.out.println(item.getDisplayString());
        }

    }

}