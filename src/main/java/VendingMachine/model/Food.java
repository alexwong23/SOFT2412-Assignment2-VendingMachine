package VendingMachine.model;

public interface Food {
    FoodEnum getType();

    String getName();

    double getPrice();

    int getQuantity();

    void setQuantity(int num);

    Food clone(int quantity);

    default String getDisplayString() {
        return String.format("%-20s %-10s %-10s %-10s", getName(), getType().toString(), getPrice(), getQuantity());
    }
    
}