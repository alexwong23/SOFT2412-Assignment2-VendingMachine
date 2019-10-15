package VendingMachine.model;

public class ChipsFactory implements FoodFactory {
    @Override
    public Food makeFood() {
        return new Chips();
    }
}
