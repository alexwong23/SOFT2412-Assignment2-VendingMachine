package VendingMachine.model;

public class Drink implements Food {

    @Override
    public FoodEnum getType() {
        return FoodEnum.DRINK;
    }
}
