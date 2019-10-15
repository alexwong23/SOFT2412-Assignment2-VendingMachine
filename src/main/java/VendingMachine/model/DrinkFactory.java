package VendingMachine.model;

public class DrinkFactory implements FoodFactory {
    @Override
    public Food makeFood(String name) {
        return new Drink(name);
    }
}
