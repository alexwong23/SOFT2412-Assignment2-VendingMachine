package VendingMachine.model;

import VendingMachine.config.FoodConfig;

import java.util.ArrayList;

public class Inventory {

    static ArrayList<Food> food = new ArrayList<>();

    private static int MAX_QUANTITY = 10;

    public Inventory(ArrayList<FoodConfig> foodConfigs) {
        FoodFactory foodFactory = null;
        for (FoodConfig configItem : foodConfigs) {
            foodFactory = configItem.getType().getFactory();
            food.add(foodFactory.makeFood(configItem.getId(), configItem.getName(), configItem.getPrice(), configItem.getQuantity()));
        }
    }

    public ArrayList<Food> getAllFood() {
        return food;
    }
    
    // no error checks yet    
    public static int restockAllInventory() {
    	for (Food food : food) {
    		food.setQuantity(MAX_QUANTITY);
    	}
    	return 0;
    }
    
    public static int restockSingleInventory(int foodId) {
        for (Food item : food) {
            if (item.getId() == foodId) {
                /* If there are already MAX_QUANTITY items */
                if (item.getQuantity() == MAX_QUANTITY) {
                    return -2;
                }
                item.setQuantity(MAX_QUANTITY);
                return 0;
            }
        }
        /* This error code indicates the food item was not found */
        return -1;
    }

    public static int restockSingleInventoryByQuantity(int foodId, int quantity) {
        for (Food item : food) {
            if (item.getId() == foodId) {
                int newTotal = item.getQuantity() + quantity;
                /* If there are already MAX_QUANTITY items */
                if (newTotal > MAX_QUANTITY) {
                    return -2;
                }
                item.setQuantity(newTotal);
                return 0;
            }
        }
        /* This error code indicates the food item was not found */
        return -1;
    }

    public static int retrieveFood(int foodId, int quantity) {
        for (Food item : food) {
            if (item.getId() == foodId) {
                /* If the quantity required exceeds the stock level, return error  */
                if (quantity > item.getQuantity()) {
                    return -2;
                }
                item.setQuantity(item.getQuantity() - quantity);
                return 0;
            }
        }
        /* This error code indicates the item was not found */
        return -1;
    }



}
