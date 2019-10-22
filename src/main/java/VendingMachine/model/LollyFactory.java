package VendingMachine.model;

public class LollyFactory implements FoodFactory {
    @Override
    public Food makeFood(int id, String name, double price) {
        return new Lolly(id, name, price);
    }
}
