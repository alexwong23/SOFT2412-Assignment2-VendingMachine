package VendingMachine.model;

public class ChocolateFactory implements FoodFactory {

    @Override
    public Food makeFood(String name) {
        return new Chocolate(name);
    }
}