package VendingMachine.model;

public interface FoodFactory {
    Food makeFood(int id, String name, double price);
}