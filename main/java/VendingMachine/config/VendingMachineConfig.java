package VendingMachine.config;

import java.util.ArrayList;

public class VendingMachineConfig {
    private ArrayList<FoodConfig> foodConfigs;
    private ArrayList<CashConfig> cashConfigs;

    public VendingMachineConfig(ArrayList<FoodConfig> foodConfigs, ArrayList<CashConfig> cashConfigs) {
        this.foodConfigs = foodConfigs;
        this.cashConfigs = cashConfigs;
    }

    public ArrayList<FoodConfig> getFoodConfigs() {
        return foodConfigs;
    }

    public ArrayList<CashConfig> getCashConfigs() {
        return cashConfigs;
    }
}
