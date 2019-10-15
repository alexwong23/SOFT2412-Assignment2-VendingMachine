package VendingMachine.Food;

public interface Food {
    Type getType();
    String getName();
    double getPrice();
    int getQuantity();
    void setQuantity(int num);

    enum Type{
        CHIP,  CHOCOLATE, DRINK, LOLLIES
    }
}
