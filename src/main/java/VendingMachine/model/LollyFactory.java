package VendingMachine.model;

public class LollyFactory implements FoodFactory {
    @Override
    public Food makeFood(String name) {
        return new Lolly(name);
    }
}
