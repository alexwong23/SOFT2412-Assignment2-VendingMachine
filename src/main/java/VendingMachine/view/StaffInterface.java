package VendingMachine.view;

import VendingMachine.model.Food;
import VendingMachine.model.VendingMachine;

public class StaffInterface implements CommandLineInterface {
	private VendingMachine vd;

    public StaffInterface(VendingMachine vendingMachine) {
    	this.vd = vendingMachine;
        System.out.println("=========Welcome to vending machine!=========");
        printFoodList();


        /* Code to restock entire inventory to MAX */
        vd.getInventory().restockAllInventory();

        /* Code to restock entire single item to MAX */
        vd.getInventory().restockSingleInventory(1);

        /* Code to add food to inventory */
        int result = vd.getInventory().restockSingleInventory(1);


        /* Retrives food that has an id of 1 */
        Food foodItem = vd.getInventory().getFoodById(1);
        if (foodItem == null) {
            /* Food id doesn't exist */
        }





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