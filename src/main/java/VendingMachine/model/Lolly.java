package VendingMachine.model;

public class Lolly implements Food {

    @Override
    public FoodEnum getType() {
        return FoodEnum.LOLLY;
    }
}