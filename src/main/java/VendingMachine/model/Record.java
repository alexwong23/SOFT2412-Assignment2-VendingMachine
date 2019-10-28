package VendingMachine.model;

import java.util.Date;

public class Record {
    private Date date;
    private String description;
    private InventoryItem item;
    private TransactionEnum transactionType;

    public Record(Date date, String description, InventoryItem item, TransactionEnum transactionType) {
        this.date = date;
        this.description = description;
        this.item = item;
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public InventoryItem getItem() {
        return item;
    }

    public TransactionEnum getTransactionType() {
        return transactionType;
    }

    public String getDisplayString() {
        return String.format("%-10s%-20s%-10s%-10s", this.date, this.description, this.item, this.transactionType);
    }
}
