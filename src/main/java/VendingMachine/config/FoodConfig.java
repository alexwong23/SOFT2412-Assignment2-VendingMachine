package VendingMachine.config;

import VendingMachine.model.FoodEnum;

public class FoodConfig {
    private String name;
    private FoodEnum type;
    private double price;
    private int quantity;

    public FoodConfig(String name, FoodEnum type, double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public FoodEnum getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}