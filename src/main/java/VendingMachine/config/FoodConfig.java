package VendingMachine.config;

import VendingMachine.model.FoodEnum;

public class FoodConfig {
    private String name;
    private FoodEnum type;
    private double price;
    private int quantity;
    private int id;

    public FoodConfig(int id, String name, FoodEnum type, double price, int quantity) {
        this.id = id;
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

    public int getId() { return id; };
}