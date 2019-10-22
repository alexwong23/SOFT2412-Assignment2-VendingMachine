package VendingMachine.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import VendingMachine.model.Food;
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
                		Date date = new Date();
                		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                		System.out.println("Success: All Inventory Restocked @ " + formatter.format(date));
                	} else {
                		System.out.println("Error: Restock All Inventory Failed.");
                	}
                    break;
                case "2":
                	printVendingMachine();
                	Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Food ID: ");
                    int id = Integer.parseInt(sc.next());
                    int restockStatus = vd.getInventory().restockSingleInventory(id);
                	if(restockStatus == 0) {
                		Date date = new Date();
                		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                		System.out.println("Success: Single Inventory Restocked @ " + formatter.format(date));
                	} else if(restockStatus == -2) {
                		System.out.println("Error: Item already has max quantity.");
                	} else {
                		System.out.println("Error: Item could not be found.");
                	}
                    break;
                case "3":
                    printVendingMachine();
                    break;
                case "4":
                	printMainMenu();
                    break;
                case "5":
                    System.out.println("Staff has Quit!");
                    System.exit(0);
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
    	System.out.println("Staff Options:");
        System.out.println("1. Restock All Items");
        System.out.println("2. Restock a Single Item");
        System.out.println("3. List All Items");
        System.out.println("4. List Staff Options");
        System.out.println("5. Quit");
        System.out.println("Choose an option:");
    }
}
