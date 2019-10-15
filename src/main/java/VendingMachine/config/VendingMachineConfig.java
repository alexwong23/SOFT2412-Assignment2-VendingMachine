package VendingMachine.config;

import java.util.ArrayList;

public class VendingMachineConfig {
    private ArrayList<FoodConfig> foodConfigs;

    public VendingMachineConfig(ArrayList<FoodConfig> foodConfigs) {
        this.foodConfigs = foodConfigs;
    }

    public ArrayList<FoodConfig> getFoodConfigs() {
        return foodConfigs;
    }
}
