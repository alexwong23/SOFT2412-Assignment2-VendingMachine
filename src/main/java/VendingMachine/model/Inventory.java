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

    public InventoryItem getInventoryItemByFoodId(int foodId) {
        for (InventoryItem item : inventoryItems) {
            if (item.getFood().getId() == foodId) {
                return item;
            }
        }
        return null;
    }

}
