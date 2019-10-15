package VendingMachine.model;

public class Chocolate implements Food {

    @Override
    public FoodEnum getType() {
        return FoodEnum.CHOCOLATE;
    }
}