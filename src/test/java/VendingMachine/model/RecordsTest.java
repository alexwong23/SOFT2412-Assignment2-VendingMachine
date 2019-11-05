/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package VendingMachine.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecordsTest {
    @Test
    public void testRecords() {
        Records testRecords = new Records();

        /* Should be initiated as an empty records array list */
        assertEquals(testRecords.getRecords().size(), 0);

        /* Create a new Coke */
        InventoryItem coke = new InventoryItem(new Drink(1, "Coke", 3), 1);

        String cokeDescription = "Purchasing a Coke";
        /* Add Coke to the record class */
        testRecords.addPurchaseRecord(cokeDescription, coke);

        /* Records should have a size of 1 */
        assertEquals(testRecords.getRecords().size(), 1);
        /* Description should match */
        assertEquals(testRecords.getRecords().get(0).getDescription(), cokeDescription);
        /* Food Id should match */
        assertEquals(testRecords.getRecords().get(0).getItem().getFood().getId(), 1);
        /* Should be a purchase */
        assertEquals(testRecords.getRecords().get(0).getTransactionType(), TransactionEnum.PURCHASE);

        /* Create a new Chip */
        InventoryItem chips = new InventoryItem(new Drink(2, "Chips", 4), 1);
        /* Cancel chip order */
        testRecords.addCancellationRecord("Cancelling Chips", chips);

        /* Records should have a size of 2 */
        assertEquals(testRecords.getRecords().size(), 2);
        /* Id should be that of the chips */
        assertEquals(testRecords.getRecords().get(1).getItem().getFood().getId(), 2);
        /* Should be a cancelled transaction */
        assertEquals(testRecords.getRecords().get(1).getTransactionType(), TransactionEnum.CANCELLATION);

    }
}