package VendingMachine.model;

public interface Food {
    FoodEnum getType();

    String getName();

    double getPrice();

    Food clone();

    int getId();

    
}