package VendingMachine.model;

import VendingMachine.config.FoodConfig;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Food> food = new ArrayList<>();

    private static int MAX_QUANTITY = 10;

    Inventory(ArrayList<FoodConfig> foodConfigs) {
        FoodFactory foodFactory = null;
        for (FoodConfig configItem : foodConfigs) {
            foodFactory = configItem.getType().getFactory();
            food.add(foodFactory.makeFood(configItem.getId(), configItem.getName(), configItem.getPrice(), configItem.getQuantity()));
        }
    }

    ArrayList<Food> getAllFood() {
        return food;
    }
    
    // no error checks yet    
    public int restockAllInventory() {
    	for (Food food : food) {
    		food.setQuantity(MAX_QUANTITY);
    	}
    	return 0;
    }
    
    public int restockSingleInventory(int foodId) {
        Food item = getFoodById(foodId);
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

    public int addFoodToInventory(int foodId, int quantity) {
        Food item = getFoodById(foodId);
        if (item == null) {
            /* This error code indicates the item was not found */
            return -1;
        }

        int newTotal = item.getQuantity() + quantity;
        /* If there are already MAX_QUANTITY items */
        if (newTotal > MAX_QUANTITY) {
            return -2;
        }
        item.setQuantity(newTotal);
        return 0;
    }

    public int removeFoodFromInventory(int foodId, int quantity) {

        Food item = getFoodById(foodId);
        if (item == null) {
            /* This error code indicates the item was not found */
            return -1;
        }
        /* If the quantity required exceeds the stock level, return error  */
        if (quantity > item.getQuantity()) {
            return -2;
        }
        item.setQuantity(item.getQuantity() - quantity);
        return 0;
    }

    public Food getFoodById(int foodId) {
        for (Food item : food) {
            if (item.getId() == foodId) {
                return item;
            }
        }
        return null;
    }

}
