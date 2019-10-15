package VendingMachine.model;

public class DrinkFactory implements FoodFactory {
    @Override
    public Food makeFood() {
        return new Drink();
    }
}
