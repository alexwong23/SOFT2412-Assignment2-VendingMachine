package VendingMachine.view;//package VendingMachine.view;

import VendingMachine.ConfigReader;
import VendingMachine.config.VendingMachineConfig;
import VendingMachine.model.VendingMachine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StaffInterfaceTest {

    private VendingMachine vd;
    private StaffInterface si;

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    String options = "Staff Options:\n"
            + "\t1. Refill All Items\n"
            + "\t2. Refill Single Item\n"
            + "\t3. List All Items\n"
            + "\t4. Refill Cash\n"
            + "\t5. List Cash\n"
            + "\t6. List Records\n"
            + "\t7. Quit\n"
            + "Choose an option:\n";

    @Before
    public void setUp() throws Exception {
        VendingMachineConfig vendingConfig = ConfigReader.readFoodNCashConfigs("src/test/resources/config.json");
        vd = new VendingMachine(vendingConfig);
        si = new StaffInterface(vd);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    private String getOutput() { // return OutputStream as a String
        System.out.flush();
        return outputStream.toString();
    }
    private void setInput(String userInput) { // set System.in to inputStream
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void falseStaffIDCheck(){
        assertEquals(false, si.StaffIDCheck("123"));
    }

    @Test
    public void trueStaffIDCheck(){
        assertEquals(true, si.StaffIDCheck("1234"));
    }

    @Test
    public void falsePrintMainMenu(){
        si.printMainMenu();
        assertNotEquals("Choose an option:\n", getOutput());
    }

    @Test
    public void truePrintMainMenu(){
        si.printMainMenu();
        assertEquals(options, getOutput());
    }

    @Test()
    public void falseStaffQuit(){
        setInput("808\n8080");
        String s = "=========Welcome Staff!=========\n"
                + options
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(s, getOutput());
    }

    @Test()
    public void trueStaffQuit(){
        setInput("8080\n");
        String s = "=========Welcome Staff!=========\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(s, getOutput());
    }

    @Test()
    public void trueStaffFoodRefill(){
        setInput("1\n8080\n");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String expectedString = "=========Welcome Staff!=========\n"
                + options
                + vd.foodToString() + "\n"
                + "All Inventory Restocked @ "
                + formatter.format(date) + "\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(expectedString, getOutput());
    }

    @Test()
    public void falseStaffFoodSingleRefill(){
        setInput("2\n6\n8080\n");
        String expectedString = "=========Welcome Staff!=========\n"
                + options
                + vd.foodToString() + "\n"
                + "Enter ID:\n"
                + "Item has maximum quantity.\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(expectedString, getOutput());
    }

    @Test()
    public void falseStaffFoodSingleRefill2(){
        setInput("2\n11\n8080\n");
        String expectedString = "=========Welcome Staff!=========\n"
                + options
                + vd.foodToString() + "\n"
                + "Enter ID:\n"
                + "Item could not be found.\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(expectedString, getOutput());
    }

    @Test()
    public void falseStaffFoodSingleRefill3(){
        setInput("2\na\n8080\n");
        String expectedString = "=========Welcome Staff!=========\n"
                + options
                + vd.foodToString() + "\n"
                + "Enter ID:\n"
                + "Invalid ID\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(expectedString, getOutput());
    }

    @Test()
    public void trueStaffFoodSingleRefill(){
        setInput("2\n7\n8080\n");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String expectedString = "=========Welcome Staff!=========\n"
                + options
                + vd.foodToString() + "\n"
                + "Enter ID:\n"
                + "Single Inventory Restocked @ "
                + formatter.format(date) + "\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(expectedString, getOutput());
    }

    @Test()
    public void trueStaffPrintFood(){
        setInput("3\n8080\n");
        String s = "=========Welcome Staff!=========\n"
                + options
                + vd.foodToString() + "\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(s, getOutput());
    }

    @Test()
    public void trueStaffCashRefill(){
        setInput("4\n8080\n");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String expectedString = "=========Welcome Staff!=========\n"
                + options
                + vd.cashToString() + "\n"
                + "Cash Refilled @ "
                + formatter.format(date) + "\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(expectedString, getOutput());
    }

    @Test()
    public void trueStaffPrintCash(){
        setInput("5\n8080\n");
        String s = "=========Welcome Staff!=========\n"
                + options
                + vd.cashToString() + "\n"
                + options
                + "Staff Quit!\n";
        si.run();
        assertEquals(s, getOutput());
    }
}


