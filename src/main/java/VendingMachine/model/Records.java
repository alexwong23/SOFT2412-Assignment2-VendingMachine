package VendingMachine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Records {
    /*
    * Have a method for each type of transaction change. One for cancellation, another for purchase.
    * */
    private List<Record> records;

    public Records() {
        this.records = new ArrayList<>();
    }

    public void addPurchaseRecord(String description, InventoryItem item) {
        this.records.add(new Record(new Date(), description, item, TransactionEnum.PURCHASE));
    }

    public void addCancellationRecord(String description, InventoryItem item) {
        this.records.add(new Record(new Date(), description, item, TransactionEnum.CANCELLATION));
    }

    public List<Record> getRecords() {
        return records;
    }

}
