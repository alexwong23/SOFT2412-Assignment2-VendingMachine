package VendingMachine.model;

import java.util.ArrayList;
import java.util.List;

public class Records {
    /* Transactions,
    * date time / description / item / action /
    *  */

    /*
    * Have a method for each type of transaction change. One for cancellation, another for purchase.
    * */
    private List<Record> records;

    public Records() {
        this.records = new ArrayList<>();

    }
}
