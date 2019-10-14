package VendingMachine.Food;

public interface Food {
    Type getType();
    String getBrand();
    double getPrice();
    int getQuantity();
    void setQuantity(int num);

    enum Type{
        CHIP,  CHOCOLATE, DRINK, LOLLIES
    }
}
