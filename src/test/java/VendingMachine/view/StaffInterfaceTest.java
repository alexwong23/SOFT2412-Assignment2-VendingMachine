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

import static org.junit.Assert.assertEquals;

public class StaffInterfaceTest {

    private VendingMachine vd;
    private StaffInterface si;

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

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
    public void truePrintMainMenu(){
        si.printMainMenu();
        assertEquals("Staff Options:\n"
                + "\t1. Refill All Items\n"
                + "\t2. Refill Single Item\n"
                + "\t3. List All Items\n"
                + "\t4. Refill Cash\n"
                + "\t5. List Cash\n"
                + "\t6. List Records\n"
                + "\t7. Quit\n"
                + "Choose an option:\n", getOutput());
    }

//    @Test
//    public void trueStaffIDCheck(){
//        String someString = "1234\n";
//        setInput(someString);
//        si.StaffIDCheck("1234");
//        String s="";
//        s+="===========Welcome to vending machine!===========\n";
//        s+="Options:\n";
//        s+="1. Customer\n";
//        s+="2. Staff\n";
//        s+="Choose an option:\n";
//        assertEquals(s,getOutput());
//    }
}


