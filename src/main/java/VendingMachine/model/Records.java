package VendingMachine.model;

import java.util.ArrayList;

public class Records {
    /* Transactions,
    * date time / description / item / action /
    *  */
    ArrayList<Transactions> myTransactions = new ArrayList<>();

    public Records(){
        myTransactions = new ArrayList<Transactions>();
    }

    /*
    * Have a method for each type of transaction change. One for cancellation, another for purchase.
    * */
    public void success(double cost, ArrayList<InventoryItem> items){
        Transactions a = new Transactions();
//        a.item = items;
        a.action = "buy";
        a.earned = cost;
        myTransactions.add(a);
    }
    public void fail(double cost, ArrayList<InventoryItem> items){
        Transactions a = new Transactions();
//        a.item = items;
        a.action = "cancel";
        a.earned = cost;
        myTransactions.add(a);
    }
}
