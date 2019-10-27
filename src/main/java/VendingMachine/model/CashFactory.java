package VendingMachine.model;

public interface CashFactory {
    Cash makeCash(int id, String name, double value);
}
