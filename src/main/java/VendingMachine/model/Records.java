package VendingMachine.model;

import java.io.*;
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
        Record record = new Record(new Date(), description, item, TransactionEnum.PURCHASE);
        this.records.add(record);
        writeToTextFile(record);
    }

    public void addCancellationRecord(String description, InventoryItem item) {
        Record record = new Record(new Date(), description, item, TransactionEnum.CANCELLATION);
        this.records.add(record);
        writeToTextFile(record);
    }
    public void writeToTextFile(Record record) {
        try(FileWriter fw = new FileWriter("src/main/resources/recordHistory.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(record.getDisplayString());
        } catch (FileNotFoundException fnfe) {
            System.out.println("Could not find file recordHistory.txt...");
        } catch (IOException ioe) {
            System.out.println("Could not write to IO...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Write to text file exception......");
        }
    }

    public List<Record> getRecords() {
        return records;
    }

}

    /* Transactions,
    * date time / description / item / action /
    *  */

    /*
    * Have a method for each type of transaction change. One for cancellation, another for purchase.
    * */
