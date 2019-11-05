package VendingMachine.model;

public class Lolly implements Food {

   private String name;
   private double price;
   private int id;

    public Lolly(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
    public Food clone() {
        return new Lolly(this.id, this.name, this.price);
    }

    @Override
    public int getId() {
        return id;
    }
}