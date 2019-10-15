package VendingMachine.model;

public class Chocolate implements Food {

    String name;

    public Chocolate(String name) {
        this.name = name;
    }

    @Override
    public FoodEnum getType() {
        return FoodEnum.CHOCOLATE;
    }

    @Override
    public String getName() {
        return name;
    }
}