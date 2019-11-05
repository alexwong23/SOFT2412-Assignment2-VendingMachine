
package VendingMachine.view;

import VendingMachine.config.VendingMachineConfig;
import VendingMachine.model.Drink;
import VendingMachine.model.Inventory;
import VendingMachine.model.InventoryItem;
import VendingMachine.model.VendingMachine;
import VendingMachine.ConfigReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CustomerInterfaceTest {

    private VendingMachine vd;
    private CustomerInterface ci;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    String mainmenu = "===========Welcome to vending machine!================\n" +
            "ID   Items               Type      Price     Quantity  \n" +
            "------------------------------------------------------\n" +
            "1    Pepsi               DRINK     5.0       10        \n" +
            "2    Sprite              DRINK     5.0       10        \n" +
            "3    Coke                DRINK     5.0       10        \n" +
            "4    Orange Juice        DRINK     5.0       10        \n" +
            "5    Red Rock Deli       CHIPS     5.0       10        \n" +
            "6    Mars bar            CHOCOLATE 5.0       10        \n" +
            "7    Jelly Beans         LOLLY     10.0      5         \n" +
            "======================================================\n" +
            "Options:\n" +
            "1. Purchase\n" +
            "2. Shopping Cart\n" +
            "3. Quit\n" +
            "Enter your options:\n";

    @Before
    public void setUp() throws Exception {
        VendingMachineConfig vendingConfig = ConfigReader.readFoodNCashConfigs("src/test/resources/config.json");
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

        String s="";
        s+="Options:\n";
        s+="1. Purchase\n";
        s+="2. Shopping Cart\n";
        s+="3. Quit\n";
        s+="Enter your options:\n";

        assertEquals(s,getOutput());
    }

    @Test
    public void run1(){
        //scenario: just quit
        setInput("3\n");

        ci.run();

        String s="";
        s+="===========Welcome to vending machine!================\n";
        s+=vd.foodToString();
        s+="Options:\n";
        s+="1. Purchase\n";
        s+="2. Shopping Cart\n";
        s+="3. Quit\n";
        s+="Enter your options:\n";

        s+="Thank you!\n";

        String result = getOutput();
        System.out.println(result);
        assertEquals(s, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void run2() {
        //scenario: in the main menu, enter an option which is not 1-3
        //need to fix
        String in = "";
        in += "s\n";        //"s-taff"
        in += "5\n";        //noise
        in += "t\n";        //"s-t-aff"
        in += "-1\n";       //noise
        //in += "aff\n";      //"st-aff"
        in += "Wrong_Id\n";     //staff id

        setInput(in);

        ci.run();

        String out = "";
        out +="";
        assertEquals(out, getOutput());
    }

    @Test
    public void purchaseInterface1() {
        //scenario: add item into shopping cart, but did not make a purchase
        String expected = "";
        expected += mainmenu +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       10        \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Add successful.\n" +
                "Continue Shopping? (Y|N)\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        String in = "";
        in += "1\n";      //Option: 1
        in += "1\n";      //enter id: 1
        in += "1\n";      //enter qua:1
        in += "n\n";      //continue shopping? (n)
        in += "3\n";      //quit

        setInput(in);

        ci.run();

        assertEquals(expected,getOutput());
    }

    @Test
    public void purchaseInterface2() {
        //scenario: add item into shopping cart, and continue shopping
        String in = "";
        in += "1\n";      //get into purchase interface
        in += "1\n";      //enter id: 1
        in += "1\n";      //enter qua:1
        in += "Y\n";      //continue shopping? (Y)
        in += "2\n";      //enter id: 2
        in += "2\n";      //enter qua:2
        in += "N\n";      //continue shopping? (N)
        in += "3\n";      //quit

        setInput(in);

        String s = "";
        s += mainmenu +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       10        \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Add successful.\n" +
                "Continue Shopping? (Y|N)\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       9         \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Add successful.\n" +
                "Continue Shopping? (Y|N)\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        ci.run();
        assertEquals(s, getOutput());
    }

    @Test
    public void purchaseInterface3() {
        //scenario: input invalid character id
        String in = "";
        in += "1\n";      //get into purchase interface
        in += "a\n";      //enter id: a
        in += "1\n";      //enter qua:1
        in += "n\n";      //continue shopping? (n)
        in += "3\n";      //quit

        setInput(in);

        String s = "";
        s += mainmenu +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       10        \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Invalid ID or Quantity\n" +
                "Continue Shopping? (Y|N)\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        ci.run();
        assertEquals(s, getOutput());
    }

    @Test
    public void purchaseInterface4() {
        //scenario: input invalid negative id
        String in = "";
        in += "1\n";      //get into purchase interface
        in += "-1\n";     //enter id: -1
        in += "1\n";      //enter qua:1
        in += "n\n";      //continue shopping? (n)
        in += "3\n";      //quit

        setInput(in);

        String s = "";
        s += mainmenu +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       10        \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Sorry, invalid ID provided.\n" +
                "Continue Shopping? (Y|N)\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        ci.run();
        assertEquals(s, getOutput());
    }

    @Test
    public void purchaseInterface5() {
        //scenario: input invalid character quantity
        String in = "";
        in += "1\n";      //get into purchase interface
        in += "1\n";      //enter id: 1
        in += "s\n";      //enter qua:s
        in += "n\n";      //continue shopping? (n)
        in += "3\n";      //quit

        setInput(in);

        String s = "";
        s += mainmenu +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       10        \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Invalid ID or Quantity\n" +
                "Continue Shopping? (Y|N)\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        ci.run();
        assertEquals(s, getOutput());
    }

    @Test
    public void purchaseInterface6() {
        //scenario: input invalid negative qua
        String in = "";
        in += "1\n";      //get into purchase interface
        in += "1\n";      //enter id: 1
        in += "-1\n";      //enter qua:-1
        in += "n\n";      //continue shopping? (n)
        in += "3\n";      //quit

        setInput(in);

        String s = "";
        s += mainmenu +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       10        \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Sorry, invalid quantity provided.\n" +
                "Continue Shopping? (Y|N)\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        ci.run();
        assertEquals(s, getOutput());
    }

    @Test
    public void printCurrencyList(){
        ci.printCurrencyList();

        String s="";
        s+="How would you like to pay?\n";
        s+="AUD\n";
        s+="USD\n";
        s+="NZD\n";
        s+="SGD\n";
        s+="CAD\n";
        s+="Enter your selection: \n";

        assertEquals(s,getOutput());
    }

    @Test(expected = NullPointerException.class)
    public void shoppingCartInterface1() {
        //scenario: delete itemm when cart is empty
        String in = "";
        in += "1\n";      //Delete Item option
        in += "1\n";      //Enter id:1
        in += "n\n";      //continue shopping? (Y)

        setInput(in);

        ci.shoppingCartInterface();
    }

    @Test
    public void shoppingCartInterface2() {
        //scenario: delete item
        InventoryItem test = vd.getInventory().getInventoryItemByFoodId(1);

        ci.getShoppingCart().addToCart(test, 3);    //add 2 id 1 object into shopping cart

        String in = "";
        in += "2\n";      //Get into Shopping cart interface
        in += "1\n";      //option: 1
        in += "1\n";      //enter id: 1
        in += "2\n";      //enter qua: 2
        in += "n\n";      //continue deleting? (n)
        in += "3\n";      //quit

        setInput(in);

        ci.run();

        String out = "";
        out += "Add successful.\n" +
                "===========Welcome to vending machine!================\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       7         \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       3         \n" +
                "Total Price: $ 15.0\n" +
                "\n" +
                "1. Delete Items\n" +
                "2. Checkout\n" +
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       3         \n" +
                "Total Price: $ 15.0\n" +
                "\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Delete successful.\n" +
                "Continue Deleting? (Y|N)\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";
        assertEquals(out, getOutput());
    }

    @Test
    public void shoppingCartInterface3() {
        //scenario: go to checkout interface
        InventoryItem test = vd.getInventory().getInventoryItemByFoodId(1);

        ci.getShoppingCart().addToCart(test, 3);    //add 2 id 1 object into shopping cart

        String in = "";
        in += "2\n";      //option: 2
        in += "USD\n";      //enter currency: USD
        //After that it goes into payment interface


        setInput(in);

        //ci.shoppingCartInterface();

        String out = "";
        out += "Add successful.\n" +
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       3         \n" +
                "Total Price: $ 15.0\n" +
                "\n" +
                "1. Delete Items\n" +
                "2. Checkout\n" +
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       3         \n" +
                "Total Price: $ 15.0\n" +
                "\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Delete successful.\n" +
                "Continue Deleting? (Y|N)\n";
        //assertEquals(out,getOutput());
    }

    @Test
    public void shoppingCartInterface4() {
        //scenario: invalid id or entity in shopping cart interface
        InventoryItem test = vd.getInventory().getInventoryItemByFoodId(1);

        ci.getShoppingCart().addToCart(test, 3);    //add 2 id 1 object into shopping cart

        String in = "";
        in += "2\n";            //get into shopping cart interface
        in += "1\n";            //option: 1
        in += "-1\n";           //invalid id input
        in += "s\n";            //invalid quantity input
        in += "n\n";            //Continue Deleting? (n)
        in += "3\n";            //quit

        setInput(in);

        ci.run();

        String out = "";
        out += "Add successful.\n" +
                "===========Welcome to vending machine!================\n" +
                vd.foodToString() +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       3         \n" +
                "Total Price: $ 15.0\n" +
                "\n" +
                "1. Delete Items\n" +
                "2. Checkout\n" +
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       3         \n" +
                "Total Price: $ 15.0\n" +
                "\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Invalid ID or Quantity\n" +
                "Continue Deleting? (Y|N)\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";
        assertEquals(out,getOutput());
    }

    @Test
    public void staffInterface(){
        //scenario: enter 'staff' at main menu and fail to log in the staff interface
        String in = "";
        in += "staff\n";          //get into the staff interface
        in += "WrongId\n";        //input a invalid staff id
        in += "3\n";

        setInput(in);

        ci.run();

        String out = mainmenu + "Enter your staff id:\n" +
                "invalid staff id\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        assertEquals(out,getOutput());
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