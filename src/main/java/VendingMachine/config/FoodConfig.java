package VendingMachine.config;

import VendingMachine.model.FoodEnum;

public class FoodConfig {
    private String name;
    private FoodEnum type;

    public FoodConfig(String name, FoodEnum type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public FoodEnum getType() {
        return type;
    }
}