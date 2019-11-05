package VendingMachine.model;

import VendingMachine.config.FoodConfig;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<InventoryItem> inventoryItems = new ArrayList<>();

    private static int MAX_QUANTITY = 10;

    Inventory(ArrayList<FoodConfig> foodConfigs) {
        FoodFactory foodFactory = null;
        for (FoodConfig configItem : foodConfigs) {
            foodFactory = configItem.getType().getFactory();
            Food food = foodFactory.makeFood(configItem.getId(), configItem.getName(), configItem.getPrice());
            inventoryItems.add(new InventoryItem(food, configItem.getQuantity()));
        }
    }

    public ArrayList<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    // no error checks yet
    public int restockAllInventory() {
    	for (InventoryItem item : inventoryItems) {
    		item.setQuantity(MAX_QUANTITY);
    	}
    	return 0;
    }

    public int restockSingleInventory(int foodId) {
        InventoryItem item = getInventoryItemByFoodId(foodId);
        if (item == null) {
            /* This error code indicates the item was not found */
            return -1;
        }

        if (item.getQuantity() >= MAX_QUANTITY) {
            return -2;
        }
        item.setQuantity(MAX_QUANTITY);
        return 0;
    }
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

    public InventoryItem getInventoryItemByFoodId(int foodId) {
        for (InventoryItem item : inventoryItems) {
            if (item.getFood().getId() == foodId) {
                return item;
            }
        }
        return null;
    }

}
