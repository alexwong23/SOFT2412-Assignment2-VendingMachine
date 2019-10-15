package VendingMachine.model;

public interface Food {
    FoodEnum getType();

    String getName();

    default String getDisplayString() {
        return String.format("%s %s", getName(), getType().toString());
    }
}