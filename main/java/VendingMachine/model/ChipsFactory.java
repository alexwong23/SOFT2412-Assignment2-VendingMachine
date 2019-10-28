package VendingMachine.model;

public class ChipsFactory implements FoodFactory {
    @Override
    public Food makeFood(int id, String name, double price) {
        return new Chips(id, name, price);
    }
}
