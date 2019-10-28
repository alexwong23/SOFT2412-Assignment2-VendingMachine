package VendingMachine.view;

import VendingMachine.model.VendingMachine;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StaffInterface implements CommandLineInterface {
	private VendingMachine vd;
    private static String[] staffIdArray = staffIdArray = new String[]{"1234", "2345"};
    private static List<String> staffIdList = staffIdList = Arrays.asList(staffIdArray);

    public StaffInterface(VendingMachine vendingMachine) {
    	this.vd = vendingMachine;
        System.out.println("=========Welcome Staff!=========");
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
                    System.out.println(vd.foodToString());
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
                    System.out.println(vd.foodToString());
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
                    System.out.println(vd.foodToString());
                    break;
                }
                case "4": {
                    System.out.println(vd.cashToString());
                    if (vd.getCoffer().refillCash() == 0) {
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        System.out.println("Cash Refilled @ " + formatter.format(date));
                    } else {
                        System.out.println("Refill Cash Failed.");
                    }
                    break;
                }
                case "5": {
                    System.out.println(vd.cashToString());
                    break;
                }
                case "6": {
                    System.out.println(vd.recordsToString());
                    break;
                }
                case "7": {
                    System.out.println("Staff has Quit!");
                    System.exit(0);
                    break;
                }
            }
        }
    }

    public void printMainMenu(){
    	System.out.println("Staff Options:");
        System.out.println("\t1. Refill All Items");
        System.out.println("\t2. Refill Single Item");
        System.out.println("\t3. List All Items");
        System.out.println("\t4. Refill Cash");
        System.out.println("\t5. List Cash");
        System.out.println("\t6. List Records");
        System.out.println("\t7. Quit");
        System.out.println("Choose an option:");
    }
}
