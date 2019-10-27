package VendingMachine.model;

import VendingMachine.config.CashConfig;

import java.util.ArrayList;

public class Coffer {

    private ArrayList<CofferDenomination> cofferDenominations = new ArrayList<>();

    private static int MAX_QUANTITY = 10;

    Coffer(ArrayList<CashConfig> cashConfigs) {
        CashFactory cashFactory = null;
        for (CashConfig configItem : cashConfigs) {
            cashFactory = configItem.getType().getFactory();
            Cash cash = cashFactory.makeCash(configItem.getId(), configItem.getName(), configItem.getValue());
            cofferDenominations.add(new CofferDenomination(cash, configItem.getQuantity()));
        }
    }

    public ArrayList<CofferDenomination> getCofferDenominations() {
        return cofferDenominations;
    }
//
//    // no error checks yet
//    public int restockAllInventory() {
//    	for (InventoryItem item : inventoryItems) {
//    		item.setQuantity(MAX_QUANTITY);
//    	}
//    	return 0;
//    }
//
//    public int restockSingleInventory(int foodId) {
//        InventoryItem item = getInventoryItemByFoodId(foodId);
//        if (item == null) {
//            /* This error code indicates the item was not found */
//            return -1;
//        }
//
//        if (item.getQuantity() >= MAX_QUANTITY) {
//            return -2;
//        }
//        item.setQuantity(MAX_QUANTITY);
//        return 0;
//    }
//
//    public int addFoodToInventory(int foodId, int quantity) {
//        InventoryItem item = getInventoryItemByFoodId(foodId);
//        if (item == null) {
//            /* This error code indicates the item was not found */
//            return -1;
//        }
//
//        int newTotal = item.getQuantity() + quantity;
//        /* If there are already MAX_QUANTITY items */
//        if (newTotal > MAX_QUANTITY) {
//            return -2;
//        }
//        item.setQuantity(newTotal);
//        return 0;
//    }
//
//    public int removeFoodFromInventory(int foodId, int quantity) {
//
//        InventoryItem item = getInventoryItemByFoodId(foodId);
//        if (item == null) {
//            /* This error code indicates the item was not found */
//            return -1;
//        }
//        /* If the quantity required exceeds the stock level, return error  */
//        if (quantity > item.getQuantity()) {
//            return -2;
//        }
//        item.setQuantity(item.getQuantity() - quantity);
//        return 0;
//    }
//
    public CofferDenomination getDenominationByCashId(int cashId) {
        for (CofferDenomination denomination : cofferDenominations) {
            if (denomination.getCash().getId() == cashId) {
                return denomination;
            }
        }
        return null;
    }

}
