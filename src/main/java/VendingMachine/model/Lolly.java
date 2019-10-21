package VendingMachine.model;

public class Lolly implements Food {

    String name;
    double price;
    int quantity;
    int id;

    public Lolly(int id, String name, double price, int quantity) {
        this.id = id;
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

    @Override
    public void setQuantity(int num) {
        this.quantity = num;
    }

    @Override
    public Food clone(int quantity) {
        return new Lolly(this.id, this.name, this.price, quantity);
    }

    @Override
    public int getId() {
        return id;
    }
}