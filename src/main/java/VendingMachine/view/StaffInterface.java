package VendingMachine.view;

import java.util.Scanner;

import VendingMachine.model.Food;
import VendingMachine.model.ShoppingCart;
import VendingMachine.model.VendingMachine;

public class StaffInterface implements CommandLineInterface {
	private VendingMachine vd;

    public StaffInterface(VendingMachine vendingMachine) {
    	this.vd = vendingMachine;
        System.out.println("=========Welcome staff!=========");
        commandLine();
    }

    public void commandLine() {
    	Scanner staffScanner = new Scanner(System.in);
        while(true){
        	printMainMenu();
            String staffInput = staffScanner.next();
            switch (staffInput){
                case "1":
                	printVendingMachine();
                	if(vd.getInventory().restockAllInventory() == 0) {
                		System.out.println("Success: All Inventory Restocked.");
                	} else {
                		System.out.println("Error: Restock All Inventory Failed.");
                	}
                    break;
                case "2":
                	printVendingMachine();
                	Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Food ID: ");
                    int id = Integer.parseInt(sc.next());
                	if(vd.getInventory().restockSingleInventory(id) == 0) {
                		System.out.println("Success: Single Inventory Restocked.");
                	} else if(vd.getInventory().restockSingleInventory(id) == -2) {
                		System.out.println("Error: Item already has max quantity.");
                	} else {
                		System.out.println("Error: Item could not be found.");
                	}
                    break;
                case "3":
                	printMainMenu();
                    break;
                case "4":
                    System.exit(1);
                    break;
            }
        }    
    }
	    
    public void printVendingMachine(){
        System.out.println("===========Welcome to vending machine!===========");
        System.out.printf("%-5s%-20s%-10s%-10s%-10s\n","ID","Items","Type","Price","Qua");
        System.out.println("-------------------------------------------------");
        for (Food food :vd.getAllFood()) {
            System.out.printf("%s\n",food.getDisplayString());
        }
        System.out.println("=================================================");
    }

    public void printMainMenu(){
    	System.out.println("Options:");
        System.out.println("1. Restock All Items");
        System.out.println("2. Restock a Single Item");
        System.out.println("3. List All Options");
        System.out.println("4. Quit");
        System.out.println("Enter your option:");
    }
}