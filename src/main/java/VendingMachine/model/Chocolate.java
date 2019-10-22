package VendingMachine.model;

public class Chocolate implements Food {

    String name;
    double price;
    int id;

    public Chocolate(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public FoodEnum getType() {
        return FoodEnum.CHOCOLATE;
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
    public Food clone() {
        return new Chocolate(this.id, this.name, this.price);
    }

    @Override
    public int getId() {
        return id;
    }
}