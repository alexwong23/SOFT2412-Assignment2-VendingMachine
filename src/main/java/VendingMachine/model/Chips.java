package VendingMachine.model;

public class Chips implements Food {

    String name;

    public Chips(String name) {
        this.name = name;
    }

    @Override
    public FoodEnum getType() {
        return FoodEnum.CHIPS;
    }

    @Override
    public String getName() {
        return name;
    }
}
