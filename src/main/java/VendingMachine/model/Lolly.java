package VendingMachine.model;

public class Lolly implements Food {

    String name;

    public Lolly(String name) {
        this.name = name;
    }

    @Override
    public FoodEnum getType() {
        return FoodEnum.LOLLY;
    }

    @Override
    public String getName() {
        return name;
    }
}