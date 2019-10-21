package VendingMachine.model;

public class Chips implements Food {

    String name;
    double price;
    int quantity;
    int id;

    public Chips(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int num) {
        this.quantity = num;
    }

    @Override
    public Food clone(int quantity) {
        return new Chips(this.id, this.name, this.price, quantity);
    }

    @Override
    public int getId() {
        return id;
    }
}
