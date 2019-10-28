package VendingMachine.config;

import VendingMachine.model.CashEnum;

public class CashConfig {
    private String name;
    private CashEnum type;
    private double value;
    private int quantity;
    private int id;

    public CashConfig(int id, String name, CashEnum type, double value, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public CashEnum getType() {
        return type;
    }

    public double getValue() { return value; }

    public int getQuantity() {
        return quantity;
    }

    public int getId() { return id; };
}
