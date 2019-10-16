package VendingMachine.model;

public class Lolly implements Food {

    String name;
    double price;
    int quantity;

    public Lolly(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public FoodEnum getType() {
        return FoodEnum.LOLLY;
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