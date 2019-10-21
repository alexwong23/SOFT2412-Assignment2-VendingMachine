package VendingMachine.User;

import java.util.Date;
import java.text.SimpleDateFormat;

import VendingMachine.model.Inventory;

public class Admin implements User {

    public Admin(){

    }
    
    public void restockAll() {
		// incorrect index and quantity provided
    	int restockBoolean = Inventory.restockAllInventory();
    	if(restockBoolean == -1) {
    		System.out.println("Error: Invalid input provided.");
    	} else if(restockBoolean == -2) {
    		System.out.println("Error: Max capacity reached, unable to restock.");
    	} else {
    		Date date = new Date();
    		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    		System.out.println("Success: Inventory restocked @ " + formatter.format(date));
    	}
	 }
    
    public void restockSingle(int indexFood) {
		// incorrect index and quantity provided
    	int restockBoolean = Inventory.restockSingleInventory(indexFood);
    	if(restockBoolean == -1) {
    		System.out.println("Error: Invalid input provided.");
    	} else if(restockBoolean == -2) {
    		System.out.println("Error: Max capacity reached, unable to restock.");
    	} else {
    		Date date = new Date();
    		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    		System.out.println("Success: Inventory restocked @ " + formatter.format(date));
    	}
	 }

    /**
     *  For Admin, it will return AMIN
     * @return the user's type
     */
    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }
}
