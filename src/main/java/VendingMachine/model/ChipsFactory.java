package VendingMachine.model;

public class ChipsFactory implements FoodFactory {
    @Override
    public Food makeFood(String name) {
        return new Chips(name);
    }
}
