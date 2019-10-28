package VendingMachine.model;

public class DrinkFactory implements FoodFactory {
    @Override
    public Food makeFood(int id, String name, double price) {
        return new Drink(id, name, price);
    }
}
