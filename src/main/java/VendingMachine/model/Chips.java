package VendingMachine.model;

public class Chips implements Food {

    String name;
    double price;
    int id;

    public Chips(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public FoodEnum getType() {
        return FoodEnum.CHIPS;
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
        return new Chips(this.id, this.name, this.price);
    }

    @Override
    public int getId() {
        return id;
    }
}
