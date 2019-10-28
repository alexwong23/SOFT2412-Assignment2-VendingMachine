package VendingMachine.model;

public class InventoryItem {

    Food food;
    int quantity;

    public InventoryItem(Food food, int quantity) {
        this.quantity = quantity;
        this.food = food;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) { this.quantity += quantity; }

    public void reduceQuantity(int quantity) { this.quantity -= quantity; }

    public int getQuantity() {
        return quantity;
    }

    public Food getFood() {
        return food;
    }

    public String getDisplayString() {
        return String.format("%-5d%-20s%-10s%-10s%-10s", food.getId(), food.getName(), food.getType().toString(), food.getPrice(), quantity);
    }

    public InventoryItem clone(int quantity) {
        return new InventoryItem(food, quantity);
    }

}
