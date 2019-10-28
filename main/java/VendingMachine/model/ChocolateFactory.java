package VendingMachine.model;

public class ChocolateFactory implements FoodFactory {

    @Override
    public Food makeFood(int id, String name, double price) {
        return new Chocolate(id, name, price);
    }
}