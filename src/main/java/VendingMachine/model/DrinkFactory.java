package VendingMachine.model;

public class DrinkFactory implements FoodFactory {
    @Override
    public Food makeFood(String name, double price, int quantity) {
        return new Drink(name, price, quantity);
    }
}
