package VendingMachine.model;

public class Chips implements Food {

    @Override
    public FoodEnum getType() {
        return FoodEnum.CHIPS;
    }
}
