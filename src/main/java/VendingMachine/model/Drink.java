package VendingMachine.model;

public class Drink implements Food {

    String name;
    double price;
    int quantity;

    public Drink(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public FoodEnum getType() {
        return FoodEnum.DRINK;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
