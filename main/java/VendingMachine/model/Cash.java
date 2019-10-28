package VendingMachine.model;

public interface Cash {
    CashEnum getType();

    String getName();

    double getValue();

    Cash clone();

    int getId();


}
