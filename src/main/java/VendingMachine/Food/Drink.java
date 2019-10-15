package VendingMachine.Food;

public class Drink implements Food{
    private Type type;
    private String brand;
    private double price;
    private int quantity;

    public Drink(){

    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.brand;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public void setQuantity(int num) {
        this.quantity = num;
    }
}