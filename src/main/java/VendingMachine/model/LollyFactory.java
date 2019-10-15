package VendingMachine.model;

public class LollyFactory implements FoodFactory {
    @Override
    public Food makeFood() {
        return new Lolly();
    }
}
