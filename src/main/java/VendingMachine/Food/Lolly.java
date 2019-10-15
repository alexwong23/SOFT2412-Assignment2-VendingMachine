package VendingMachine.Food;

public class Lolly implements Food{
    private Type type;
    private String name;
    private double price;
    private int quantity;

    public Lolly(){

    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
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
