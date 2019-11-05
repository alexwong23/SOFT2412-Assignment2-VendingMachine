package VendingMachine.model;

import java.text.SimpleDateFormat;
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

    // Unused Getter - commented out
    //public Date getDate() {
    //    return date;
    //}

    public InventoryItem getItem() {
        return item;
    }

    public TransactionEnum getTransactionType() {
        return transactionType;
    }

    public String getDisplayString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return String.format("%-20s%-15s%-10s%-10s%-10s",
                formatter.format(this.date),
                this.item.getFood().getName(),
                this.item.getQuantity(),
                this.item.getFood().getPrice(),
                this.transactionType.toString());
    }
}
