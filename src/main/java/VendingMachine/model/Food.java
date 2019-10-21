package VendingMachine.model;

public interface Food {
    FoodEnum getType();

    String getName();

    double getPrice();

    int getQuantity();

    void setQuantity(int num);

    Food clone(int quantity);

    default String getDisplayString() {
        return String.format("%-5d%-20s%-10s%-10s%-10s", getId(),getName(), getType().toString(), getPrice(), getQuantity());
    }

    int getId();

    
}