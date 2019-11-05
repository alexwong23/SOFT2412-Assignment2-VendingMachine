package VendingMachine.model;

public class Drink implements Food {

    private String name;
    private double price;
    private int id;

    public Drink(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
    public Food clone() {
        return new Drink(this.id, this.name, this.price);
    }

    @Override
    public int getId() {
        return id;
    }
}
