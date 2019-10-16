package VendingMachine.model;

public interface Food {
    FoodEnum getType();

    String getName();

    double getPrice();

    int getQuantity();

    default String getDisplayString() {
        return String.format("%s %s %s %s", getName(), getType().toString(), getPrice(), getQuantity());
    }
}