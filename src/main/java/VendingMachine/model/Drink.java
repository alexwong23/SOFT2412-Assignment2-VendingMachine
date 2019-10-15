package VendingMachine.model;

public class Drink implements Food {

    String name;

    public Drink(String name) {
        this.name = name;
    }

    @Override
    public FoodEnum getType() {
        return FoodEnum.DRINK;
    }

    @Override
    public String getName() {
        return name;
    }
}
