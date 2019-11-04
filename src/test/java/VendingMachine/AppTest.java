package VendingMachine;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class AppTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws Exception {
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

//    @Test
//    public void printVendingMachine(){
//        String someString = "1\n3\n";
//        setInput(someString);
//        App.VendingMachineInterface();
//        String s="";
//        s+="===========Welcome to vending machine!===========\n";
//        s+="Options:\n";
//        s+="1. Customer\n";
//        s+="2. Staff\n";
//        s+="Choose an option:\n";
//        assertEquals(s,getOutput());
//    }

}

