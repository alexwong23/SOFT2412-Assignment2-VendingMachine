package VendingMachine;

import VendingMachine.config.*;
import VendingMachine.model.*;
import VendingMachine.User.*;
import VendingMachine.view.*;
import VendingMachine.*;
import org.junit.Test;

import org.junit.Before;
import org.junit.After;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CofferUsageTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

//    @Before // execute before each test
//    //public methods run without having to be called
//    public void setOutputStream() { // set System.out to printStream
//        outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//    }
//    private String getOutputStream() { // return OutputStream as a String
//        System.out.flush();
//        return outputStream.toString();
//    }
//    private void setInputStream(String userInput) { // set System.in to inputStream
//        inputStream = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(inputStream);
//    }
//    @After // execute before each test
//    //public methods run without having to be called
//    public void resetSystemIO() { // reset System IO to original
//        System.setIn(systemIn);
//        System.setOut(systemOut);
//    }


    @Test
    public void PurchaseLoopTest() {
        VendingMachineConfig vendingConfig = ConfigReader.readFoodNCashConfigs("src/main/resources/config.json");
        VendingMachine vendingMachine = new VendingMachine(vendingConfig);

        System.out.println("===========Welcome to vending machine!===========");
//        CustomerInterface ci = CustomerInterface.testConstructor(vendingMachine);
//        boolean purchasing = true;
//        Scanner purchase_sc = new Scanner(System.in);


//        setInputStream("0");
//        String a = getOutputStream();
//        System.out.println(a);


    }
    @Test
    public void testCofferChange(){
        VendingMachineConfig vendingConfig = ConfigReader.readFoodNCashConfigs("src/main/resources/configForTest.json");
        VendingMachine vendingMachine = new VendingMachine(vendingConfig);
        Coffer coffer = vendingMachine.getCoffer();


        assertEquals(coffer.getCofferDenominations().get(0).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(1).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(2).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(3).getQuantity(),50);
        assertEquals(coffer.getCofferDenominations().get(4).getQuantity(),25);
        assertEquals(coffer.getCofferDenominations().get(5).getQuantity(),10);
        assertEquals(coffer.getCofferDenominations().get(6).getQuantity(),5);
        assertEquals(coffer.getCofferDenominations().get(7).getQuantity(),5);
        assertEquals(coffer.getCofferDenominations().get(8).getQuantity(),2);
        assertEquals(coffer.getCofferDenominations().get(9).getQuantity(),1);


        coffer.payOut(10); // lose a 10 dollar note, not another combination, siuncer that is the largest money that works


        assertEquals(coffer.getCofferDenominations().get(0).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(1).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(2).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(3).getQuantity(),50);
        assertEquals(coffer.getCofferDenominations().get(4).getQuantity(),25);
        assertEquals(coffer.getCofferDenominations().get(5).getQuantity(),10);
        assertEquals(coffer.getCofferDenominations().get(6).getQuantity(),4);
        assertEquals(coffer.getCofferDenominations().get(7).getQuantity(),5);
        assertEquals(coffer.getCofferDenominations().get(8).getQuantity(),2);
        assertEquals(coffer.getCofferDenominations().get(9).getQuantity(),1);


        coffer.payOut(0.10); //lost a 10 cent coin

        assertEquals(coffer.getCofferDenominations().get(0).getQuantity(),99);
        assertEquals(coffer.getCofferDenominations().get(1).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(2).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(3).getQuantity(),50);
        assertEquals(coffer.getCofferDenominations().get(4).getQuantity(),25);
        assertEquals(coffer.getCofferDenominations().get(5).getQuantity(),10);
        assertEquals(coffer.getCofferDenominations().get(6).getQuantity(),4);
        assertEquals(coffer.getCofferDenominations().get(7).getQuantity(),5);
        assertEquals(coffer.getCofferDenominations().get(8).getQuantity(),2);
        assertEquals(coffer.getCofferDenominations().get(9).getQuantity(),1);

        coffer.payOut(7); // lose a 5 dollar note, then a 2 dollar note.

        assertEquals(coffer.getCofferDenominations().get(0).getQuantity(),99);
        assertEquals(coffer.getCofferDenominations().get(1).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(2).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(3).getQuantity(),50);
        assertEquals(coffer.getCofferDenominations().get(4).getQuantity(),24);
        assertEquals(coffer.getCofferDenominations().get(5).getQuantity(),9);
        assertEquals(coffer.getCofferDenominations().get(6).getQuantity(),4);
        assertEquals(coffer.getCofferDenominations().get(7).getQuantity(),5);
        assertEquals(coffer.getCofferDenominations().get(8).getQuantity(),2);
        assertEquals(coffer.getCofferDenominations().get(9).getQuantity(),1);

        coffer.payOut(0); //no notes were used, therefore, no change

        assertEquals(coffer.getCofferDenominations().get(0).getQuantity(),99);
        assertEquals(coffer.getCofferDenominations().get(1).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(2).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(3).getQuantity(),50);
        assertEquals(coffer.getCofferDenominations().get(4).getQuantity(),24);
        assertEquals(coffer.getCofferDenominations().get(5).getQuantity(),9);
        assertEquals(coffer.getCofferDenominations().get(6).getQuantity(),4);
        assertEquals(coffer.getCofferDenominations().get(7).getQuantity(),5);
        assertEquals(coffer.getCofferDenominations().get(8).getQuantity(),2);
        assertEquals(coffer.getCofferDenominations().get(9).getQuantity(),1);

        coffer.payOut(100000000); //uses up more than all the notes, so it is unsuccessful

        assertEquals(coffer.getCofferDenominations().get(0).getQuantity(),99);
        assertEquals(coffer.getCofferDenominations().get(1).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(2).getQuantity(),100);
        assertEquals(coffer.getCofferDenominations().get(3).getQuantity(),50);
        assertEquals(coffer.getCofferDenominations().get(4).getQuantity(),24);
        assertEquals(coffer.getCofferDenominations().get(5).getQuantity(),9);
        assertEquals(coffer.getCofferDenominations().get(6).getQuantity(),4);
        assertEquals(coffer.getCofferDenominations().get(7).getQuantity(),5);
        assertEquals(coffer.getCofferDenominations().get(8).getQuantity(),2);
        assertEquals(coffer.getCofferDenominations().get(9).getQuantity(),1);


    }


}
