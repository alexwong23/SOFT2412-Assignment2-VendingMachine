package VendingMachine.model;

import VendingMachine.config.FoodConfig;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Food> food = new ArrayList<>();

    public Inventory(ArrayList<FoodConfig> foodConfigs) {
        FoodFactory foodFactory = null;
        for (FoodConfig configItem : foodConfigs) {
            FoodEnum foodType = configItem.getType();
            if (foodType == FoodEnum.CHIPS) {
                foodFactory = new ChipsFactory();
            } else if (foodType == FoodEnum.CHOCOLATE) {
                foodFactory = new ChocolateFactory();
            } else if (foodType == FoodEnum.DRINK) {
                foodFactory = new DrinkFactory();
            } else if (foodType == FoodEnum.LOLLY) {
                foodFactory = new LollyFactory();
            }
            food.add(foodFactory.makeFood(configItem.getName()));
        }
    }

    public ArrayList<Food> getAllFood() {
        return food;
    }

}
