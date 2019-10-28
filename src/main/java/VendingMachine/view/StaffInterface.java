package VendingMachine.view;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import VendingMachine.model.InventoryItem;
import VendingMachine.model.VendingMachine;

public class StaffInterface implements CommandLineInterface {
	private VendingMachine vd;
    private static String[] staffIdArray = staffIdArray = new String[]{"1234", "2345"};
    private static List<String> staffIdList = staffIdList = Arrays.asList(staffIdArray);

    public StaffInterface(VendingMachine vendingMachine) {
    	this.vd = vendingMachine;
        System.out.println("=========Welcome staff!=========");
        commandLine();
    }

    public static boolean StaffIDCheck(String staffId) {
        if(staffIdList.contains(staffId)) {
            return true;
        }
        return false;
    }

    public void commandLine() {
        Scanner staffScanner = new Scanner(System.in);

        while (true) {
            printMainMenu();
            String staffInput = staffScanner.nextLine();
            switch (staffInput) {
                case "1": {
                    printVendingMachine();
                    if (vd.getInventory().restockAllInventory() == 0) {
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        System.out.println("Success: All Inventory Restocked @ " + formatter.format(date));
                    } else {
                        System.out.println("Error: Restock All Inventory Failed.");
                    }
                    break;
                }
                case "2": {
                    printVendingMachine();
                    Scanner foodScanner = new Scanner(System.in);
                    System.out.println("Enter Food ID: ");
                    int id = Integer.parseInt(foodScanner.next());
                    int restockStatus = vd.getInventory().restockSingleInventory(id);
                    switch (restockStatus) {
                        case 0: {
                            Date date = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            System.out.println("Success: Single Inventory Restocked @ " + formatter.format(date));
                            break;
                        }
                        case -2: {
                            System.out.println("Error: Item has maximum quantity.");
                            break;
                        }
                        default: {
                            System.out.println("Error: Item could not be found.");
                            break;
                        }
                    }
                    foodScanner.close();
                }
                case "3": {
                    printVendingMachine();
                    break;
                }
                /*
                case "4": {
                    printMainMenu();
                    break;
                }
                 */
                case "4": {
                    System.out.println("Staff has Quit!");
                    System.exit(0);
                    break;
                }
            }
        }
    }

    public void printVendingMachine(){
        System.out.println("===========Welcome to vending machine!===========");
        System.out.printf("%-5s%-20s%-10s%-10s%-10s\n","ID","Items","Type","Price","Qua");
        System.out.println("-------------------------------------------------");
        for (InventoryItem item :vd.getInventory().getInventoryItems()) {
            System.out.printf("%s\n",item.getDisplayString());
        }
        System.out.println("=================================================");
    }

    public void printMainMenu(){
    	System.out.println("Staff Options:");
        System.out.println("\t1. Refill All");
    //    System.out.println("\t2. Refill Single");
        System.out.println("\t3. List All Items");
     //   System.out.println("\t4. List Staff Options");
        System.out.println("\t5. Quit");
        System.out.println("Choose an option:");
    }
}
