package VendingMachine.model;

public class LollyFactory implements FoodFactory {
    @Override
    public Food makeFood(String name, double price, int quantity) {
        return new Chips(name, price, quantity);
    }
}
