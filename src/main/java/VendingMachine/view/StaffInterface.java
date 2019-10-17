package VendingMachine.view;

import VendingMachine.model.Food;
import VendingMachine.model.VendingMachine;

public class StaffInterface implements CommandLineInterface {
	private VendingMachine vd;

    public StaffInterface(VendingMachine vendingMachine) {
    	this.vd = vendingMachine;
        System.out.println("=========Welcome to vending machine!=========");
        printFoodList();

        // Code that reads staff input goes here
        // Code that outputs data to staff goes here
    }

    public void printFoodList(){
        System.out.printf("%-20s%-10s%-10s%-10s\n","Items"," Type","  Qua","Price");
        System.out.println("---------------------------------------------");
        for (Food item : this.vd.getAllFood()) {
            System.out.println(item.getDisplayString());
        }
    }
}