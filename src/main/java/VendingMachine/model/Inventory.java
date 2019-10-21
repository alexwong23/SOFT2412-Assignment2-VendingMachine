package VendingMachine.model;

import VendingMachine.config.FoodConfig;

import java.util.ArrayList;

public class Inventory {

    static ArrayList<Food> food = new ArrayList<>();

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
    		food.setQuantity(10);
    	}
    	return 0;
    }
    
    public static int restockSingleInventory(int indexFood) {
    	// incorrect index and quantity provided
    	if(food.size() < indexFood + 1) {
    		return -1;
    	}
    	Food selectedFood = food.get(indexFood);
    	if(selectedFood.getQuantity() == 10) {
     		return -2;
     	}
    	selectedFood.setQuantity(10);
    	food.set(indexFood, selectedFood);
    	return 0;
    }

}
