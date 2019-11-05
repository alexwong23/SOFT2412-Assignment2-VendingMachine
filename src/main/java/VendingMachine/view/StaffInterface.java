package VendingMachine.view;

import VendingMachine.model.InventoryItem;
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

    private Scanner scanner;

    public StaffInterface(VendingMachine vendingMachine) {
    	this.vd = vendingMachine;
    }

    public static boolean StaffIDCheck(String staffId) {
        if(staffIdList.contains(staffId)) {
            return true;
        }
        return false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========Welcome Staff!=========");
        boolean staffQuit = true;
        while (staffQuit) {
            printMainMenu();
            String staffInput = scanner.next();
            switch (staffInput) {
                case "1": {
                    System.out.println(vd.foodToString());
                    if (vd.getInventory().restockAllInventory() == 0) {
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        System.out.println("All Inventory Restocked @ " + formatter.format(date));
                    } else {
                        System.out.println("Restock All Inventory Failed.");
                    }
                    break;
                }
                case "2": {
                    System.out.println(vd.foodToString());
                    System.out.println("Enter ID:");
                    String idString = scanner.next();
                    try {
                        int id = Integer.parseInt(idString);
                        int restockStatus = vd.getInventory().restockSingleInventory(id);
                        switch (restockStatus) {
                            case 0: {
                                Date date = new Date();
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                System.out.println("Single Inventory Restocked @ " + formatter.format(date));
                                break;
                            }
                            case -2: {
                                System.out.println("Item has maximum quantity.");
                                break;
                            }
                            default: {
                                System.out.println("Item could not be found.");
                                break;
                            }
                        }
                    }catch (Exception e){
                        System.out.println("Invalid ID");
                    }
                    break;
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
                    System.out.println("======All Records======\n");
                    System.out.println(vd.recordsToString());
                    break;
                }
                case "7": {
                    System.out.println("Staff Quit!");
                    staffQuit=false;
                    scanner.close();
                    break;
//                    scanner.close();
//                    staffQuit = false;
//                    exitProgram();
//                    break;
                }
            }
        }
    }

    public void exitProgram() {
        System.out.println("Staff has Quit!");
        System.exit(0);
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
