package VendingMachine.view;

import VendingMachine.config.VendingMachineConfig;
import VendingMachine.model.Inventory;
import VendingMachine.model.VendingMachine;
import VendingMachine.ConfigReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CustomerInterfaceTest {

    private VendingMachine vd;
    private CustomerInterface ci;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws Exception {
        VendingMachineConfig vendingConfig = ConfigReader.readFoodNCashConfigs("src/test/resources/config.json");
        vd = new VendingMachine(vendingConfig);
        assertNotNull(vd);

        ci = new CustomerInterface(vd);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void printVendingMachine(){
        ci.printMainMenu();

        String s="";
        s+="Options:\n";
        s+="1. Purchase\n";
        s+="2. Shopping Cart\n";
        s+="3. Quit\n";
        s+="Enter your options:\n";

        assertEquals(s,getOutput());
    }

//    @Test
//    public void run1(){
//        ci.run();
//
//        String s="";
//        s+="===========Welcome to vending machine!================\n";
//        s+=vd.foodToString();
//        s+="Options:\n";
//        s+="1. Purchase\n";
//        s+="2. Shopping Cart\n";
//        s+="3. Quit\n";
//        s+="Enter your options:\n";
//
//        assertEquals(s,getOutput());
//    }

    @Test
    public void printCurrencyList(){
        ci.printCurrencyList();

        String s="";
        s+="How would you like to pay?\n";
        s+="USD\n";
        s+="AUD\n";
        s+="CNY\n";
        s+="JPY\n";
        s+="CAD\n";
        s+="Enter your selection: \n";

        assertEquals(s,getOutput());
    }

    private String getOutput() { // return OutputStream as a String
        System.out.flush();
        return outputStream.toString();
    }

    private void setInput(String userInput) { // set System.in to inputStream
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
    }
}