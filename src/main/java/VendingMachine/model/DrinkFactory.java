package VendingMachine.model;

public class DrinkFactory implements FoodFactory {
    @Override
    public Food makeFood(int id, String name, double price, int quantity) {
        return new Drink(id, name, price, quantity);
    }
}
