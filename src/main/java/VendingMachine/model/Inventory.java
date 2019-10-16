package VendingMachine.model;

import VendingMachine.config.FoodConfig;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Food> food = new ArrayList<>();

    public Inventory(ArrayList<FoodConfig> foodConfigs) {
        FoodFactory foodFactory = null;
        for (FoodConfig configItem : foodConfigs) {
            foodFactory = configItem.getType().getFactory();
            food.add(foodFactory.makeFood(configItem.getName(), configItem.getPrice(), configItem.getQuantity()));
        }
    }

    public ArrayList<Food> getAllFood() {
        return food;
    }

}
