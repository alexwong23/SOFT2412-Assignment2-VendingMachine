package VendingMachine.model;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

=======
>>>>>>> parent of ef2af96... Now the change system works with a greedyh algorithm,. Records 'works' but needs to change the shoppingcart class to make it work
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

=======
public class Records {
    /* Transactions,
    * date time / description / item / action /
    *  */

    /*
    * Have a method for each type of transaction change. One for cancellation, another for purchase.
    * */
<<<<<<< HEAD
>>>>>>> parent of ef2af96... Now the change system works with a greedyh algorithm,. Records 'works' but needs to change the shoppingcart class to make it work
=======
>>>>>>> parent of ef2af96... Now the change system works with a greedyh algorithm,. Records 'works' but needs to change the shoppingcart class to make it work
}
