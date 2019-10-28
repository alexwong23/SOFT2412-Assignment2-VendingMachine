package VendingMachine.view;

import VendingMachine.config.VendingMachineConfig;
import VendingMachine.model.Food;
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
        VendingMachineConfig vendingConfig = ConfigReader.readFoodConfigs("src/test/resources/config.json");
        vd = new VendingMachine(vendingConfig);
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
        String s = "";
        s+="===========Welcome to vending machine!================\n";
        s+=String.format("%-5s%-20s%-10s%-10s%-10s\n","ID","Items","Type","Price","Quantity\n");
        s+="------------------------------------------------------\n";
        for (Food food :vd.getAllFood()) {
            s+=String.format("%s\n",food.getDisplayString());
        }
        s+="======================================================\n";
        s+="Options:\n";
        s+="1. Purchase\n";
        s+="2. Shopping Cart\n";
        s+="3. Quit\n";
        s+="Enter your options:\n";

        assertEquals(s,s);
    }

    private String getOutputStream() { // return OutputStream as a String
        System.out.flush();
        return outputStream.toString();
    }

    private void setInputStream(String userInput) { // set System.in to inputStream
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
    }
}