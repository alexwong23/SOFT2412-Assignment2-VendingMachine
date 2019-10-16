package VendingMachine.model;

public interface FoodFactory {
    Food makeFood(String name, double price, int quantity);
}