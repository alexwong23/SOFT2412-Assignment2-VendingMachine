package VendingMachine.model;

public class CofferDenomination {

    Cash cash;
    int quantity;

    public CofferDenomination(Cash cash, int quantity) {
        this.quantity = quantity;
        this.cash = cash;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) { this.quantity += quantity; }

    public void reduceQuantity(int quantity) { this.quantity -= quantity; }

    public int getQuantity() {
        return quantity;
    }

    public Cash getCash() {
        return cash;
    }

    public String getDisplayString() {
        return String.format("%-5d%-20s%-10s", cash.getId(), cash.getName(), cash.getType().toString());
    }
    public String getStaffDisplayString() {
        return String.format("%-5d%-20s%-10s%-10s%-10s", cash.getId(), cash.getName(), cash.getType().toString(), cash.getValue(), quantity);
    }

    public CofferDenomination clone(int quantity) {
        return new CofferDenomination(cash, quantity);
    }

}
