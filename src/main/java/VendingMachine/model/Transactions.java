package VendingMachine.model;

import java.util.ArrayList;
import java.util.Date;

public class Transactions {
    Date date;
    ArrayList<InventoryItem> item;
    String action;
    double earned;
    public Transactions(){
        Date date=java.util.Calendar.getInstance().getTime();
    }
}
