
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

public class CustomerInterfacePaymentTest {

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
    public void PurchaseToPaymentAUD100DollarsSuccess() {
        InventoryItem test = vd.getInventory().getInventoryItemByFoodId(1);

        ci.getShoppingCart().addToCart(test, 3);    //add 2 id 1 object into shopping cart

        String in = "";
        in += "2\n";      //Get into Shopping cart interface
        in += "2\n";
        in += "AUD\n";
        in += "10\n";
        in += "1\n";
        in += "y\n";
        in += "3\n";

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
                "How would you like to pay?\n" +
                "AUD\n" +
                "USD\n" +
                "NZD\n" +
                "SGD\n" +
                "CAD\n" +
                "Enter your selection: \n" +
                "You need to pay: $15.00 in AUD\n" +
                "You have paid: $0.00 in AUD\n" +
                "ID   Items               Type      \n" +
                "------------------------------------------------------\n" +
                "1    10c                 COIN      0.1       10        \n" +
                "2    20c                 COIN      0.2       10        \n" +
                "3    50c                 COIN      0.5       10        \n" +
                "4    $1                  COIN      1.0       10        \n" +
                "5    $2                  COIN      2.0       10        \n" +
                "6    $5                  NOTE      5.0       10        \n" +
                "7    $10                 NOTE      10.0      10        \n" +
                "8    $20                 NOTE      20.0      5         \n" +
                "9    $50                 NOTE      50.0      2         \n" +
                "10   $100                NOTE      100.0     1         \n" +
                "======================================================\n" +
                "\n" +
                "Return to cart: 0 \tOR\n" +
                "Enter ID: \n" +
                "Enter Quantity:\n" +
                "Cash inserted successfully.\n" +
                "change is 85.0\n" +
                "1 50.0 dollar note/coin was sent out\n" +
                "1 20.0 dollar note/coin was sent out\n" +
                "1 10.0 dollar note/coin was sent out\n" +
                "1 5.0 dollar note/coin was sent out\n" +
                "You have enough to checkout. Checkout now? (Y|N)\n" +
                "Change of $85.00 in AUD received.\n" +
                "Thank you for your purchase, come back again!\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";
        assertEquals(out, getOutput());
    }

    @Test
    public void PurchaseToPaymentAUDDifferentCashValues() {
        String in = "";
        in += "1\n"; //enter buying menu
        in += "1\n"; // buy first item
        in += "3\n"; // buy it 3 times
        in += "n\n";
        in += "2\n";      //Get into Shopping cart interface
        in += "2\n";
        in += "AUD\n";
        in += "5\n";
        in += "5\n";
        in += "6\n";
        in += "1\n";
        in += "y\n";
        in += "3\n";

        setInput(in);

        ci.run();

        String out = "";
        out +="===========Welcome to vending machine!================\n" +
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
                "Enter your options:\n" +
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
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       3         \n" +
                "Total Price: $ 15.0\n" +
                "\n" +
                "1. Delete Items\n" +
                "2. Checkout\n" +
                "How would you like to pay?\n" +
                "AUD\n" +
                "USD\n" +
                "NZD\n" +
                "SGD\n" +
                "CAD\n" +
                "Enter your selection: \n" +
                "You need to pay: $15.00 in AUD\n" +
                "You have paid: $0.00 in AUD\n" +
                "ID   Items               Type      \n" +
                "------------------------------------------------------\n" +
                "1    10c                 COIN      0.1       10        \n" +
                "2    20c                 COIN      0.2       10        \n" +
                "3    50c                 COIN      0.5       10        \n" +
                "4    $1                  COIN      1.0       10        \n" +
                "5    $2                  COIN      2.0       10        \n" +
                "6    $5                  NOTE      5.0       10        \n" +
                "7    $10                 NOTE      10.0      10        \n" +
                "8    $20                 NOTE      20.0      5         \n" +
                "9    $50                 NOTE      50.0      2         \n" +
                "10   $100                NOTE      100.0     1         \n" +
                "======================================================\n" +
                "\n" +
                "Return to cart: 0 \tOR\n" +
                "Enter ID: \n" +
                "Enter Quantity:\n" +
                "Cash inserted successfully.\n" +
                "You need to pay: $15.00 in AUD\n" +
                "You have paid: $10.00 in AUD\n" +
                "ID   Items               Type      \n" +
                "------------------------------------------------------\n" +
                "1    10c                 COIN      0.1       10        \n" +
                "2    20c                 COIN      0.2       10        \n" +
                "3    50c                 COIN      0.5       10        \n" +
                "4    $1                  COIN      1.0       10        \n" +
                "5    $2                  COIN      2.0       15        \n" +
                "6    $5                  NOTE      5.0       10        \n" +
                "7    $10                 NOTE      10.0      10        \n" +
                "8    $20                 NOTE      20.0      5         \n" +
                "9    $50                 NOTE      50.0      2         \n" +
                "10   $100                NOTE      100.0     1         \n" +
                "======================================================\n" +
                "\n" +
                "Return to cart: 0 \tOR\n" +
                "Enter ID: \n" +
                "Enter Quantity:\n" +
                "Cash inserted successfully.\n" +
                "change is 0.0\n" +
                "You have enough to checkout. Checkout now? (Y|N)\n" +
                "Change of $0.00 in AUD received.\n" +
                "Thank you for your purchase, come back again!\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        assertEquals(out, getOutput());


    }

    @Test
    public void PurchaseFullStockToPaymentToPurchase() {
        String in = "";
        in += "1\n"; //enter buying menu
        in += "1\n"; // buy first item
        in += "10\n"; // buy it 3 times
        in += "y\n";
        in += "2\n";
        in += "10\n";
        in += "y\n";
        in += "3\n";
        in += "10\n";
        in += "y\n";
        in += "4\n";
        in += "10\n";
        in += "y\n";
        in += "5\n";
        in += "10\n";
        in += "y\n";
        in += "6\n";
        in += "10\n";
        in += "y\n";
        in += "7\n";
        in += "10\n";
        in += "y\n";
        in += "8\n";
        in += "10\n";
        in += "y\n";
        in += "9\n";
        in += "10\n";
        in += "y\n";
        in += "10\n";
        in += "5\n";
        in += "n\n";
        in += "2\n";      //Get into Shopping cart interface
        in += "2\n";
        in += "AUD\n";
        in += "10\n";
        in += "4\n";
        in += "y\n";
        in += "3\n";

        setInput(in);

        ci.run();

        String out = "";
        out += "===========Welcome to vending machine!================\n" +
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
                "Enter your options:\n" +
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
                "1    Pepsi               DRINK     5.0       0         \n" +
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
                "1    Pepsi               DRINK     5.0       0         \n" +
                "2    Sprite              DRINK     5.0       0         \n" +
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
                "1    Pepsi               DRINK     5.0       0         \n" +
                "2    Sprite              DRINK     5.0       0         \n" +
                "3    Coke                DRINK     5.0       0         \n" +
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
                "1    Pepsi               DRINK     5.0       0         \n" +
                "2    Sprite              DRINK     5.0       0         \n" +
                "3    Coke                DRINK     5.0       0         \n" +
                "4    Orange Juice        DRINK     5.0       0         \n" +
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
                "1    Pepsi               DRINK     5.0       0         \n" +
                "2    Sprite              DRINK     5.0       0         \n" +
                "3    Coke                DRINK     5.0       0         \n" +
                "4    Orange Juice        DRINK     5.0       0         \n" +
                "5    Red Rock Deli       CHIPS     5.0       0         \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Add successful.\n" +
                "Continue Shopping? (Y|N)\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       0         \n" +
                "2    Sprite              DRINK     5.0       0         \n" +
                "3    Coke                DRINK     5.0       0         \n" +
                "4    Orange Juice        DRINK     5.0       0         \n" +
                "5    Red Rock Deli       CHIPS     5.0       0         \n" +
                "6    Mars bar            CHOCOLATE 5.0       0         \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Sorry, we do not have enough stock to accommodate your request.\n" +
                "Continue Shopping? (Y|N)\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       0         \n" +
                "2    Sprite              DRINK     5.0       0         \n" +
                "3    Coke                DRINK     5.0       0         \n" +
                "4    Orange Juice        DRINK     5.0       0         \n" +
                "5    Red Rock Deli       CHIPS     5.0       0         \n" +
                "6    Mars bar            CHOCOLATE 5.0       0         \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Sorry, invalid ID provided.\n" +
                "Continue Shopping? (Y|N)\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       0         \n" +
                "2    Sprite              DRINK     5.0       0         \n" +
                "3    Coke                DRINK     5.0       0         \n" +
                "4    Orange Juice        DRINK     5.0       0         \n" +
                "5    Red Rock Deli       CHIPS     5.0       0         \n" +
                "6    Mars bar            CHOCOLATE 5.0       0         \n" +
                "7    Jelly Beans         LOLLY     10.0      5         \n" +
                "======================================================\n" +
                "Enter ID:\n" +
                "Enter Quantity:\n" +
                "Sorry, invalid ID provided.\n" +
                "Continue Shopping? (Y|N)\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "------------------------------------------------------\n" +
                "1    Pepsi               DRINK     5.0       0         \n" +
                "2    Sprite              DRINK     5.0       0         \n" +
                "3    Coke                DRINK     5.0       0         \n" +
                "4    Orange Juice        DRINK     5.0       0         \n" +
                "5    Red Rock Deli       CHIPS     5.0       0         \n" +
                "6    Mars bar            CHOCOLATE 5.0       0         \n" +
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
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       10        \n" +
                "2    Sprite              DRINK     5.0       10        \n" +
                "3    Coke                DRINK     5.0       10        \n" +
                "4    Orange Juice        DRINK     5.0       10        \n" +
                "5    Red Rock Deli       CHIPS     5.0       10        \n" +
                "6    Mars bar            CHOCOLATE 5.0       10        \n" +
                "Total Price: $ 300.0\n" +
                "\n" +
                "1. Delete Items\n" +
                "2. Checkout\n" +
                "How would you like to pay?\n" +
                "AUD\n" +
                "USD\n" +
                "NZD\n" +
                "SGD\n" +
                "CAD\n" +
                "Enter your selection: \n" +
                "You need to pay: $300.00 in AUD\n" +
                "You have paid: $0.00 in AUD\n" +
                "ID   Items               Type      \n" +
                "------------------------------------------------------\n" +
                "1    10c                 COIN      0.1       10        \n" +
                "2    20c                 COIN      0.2       10        \n" +
                "3    50c                 COIN      0.5       10        \n" +
                "4    $1                  COIN      1.0       10        \n" +
                "5    $2                  COIN      2.0       10        \n" +
                "6    $5                  NOTE      5.0       10        \n" +
                "7    $10                 NOTE      10.0      10        \n" +
                "8    $20                 NOTE      20.0      5         \n" +
                "9    $50                 NOTE      50.0      2         \n" +
                "10   $100                NOTE      100.0     1         \n" +
                "======================================================\n" +
                "\n" +
                "Return to cart: 0 \tOR\n" +
                "Enter ID: \n" +
                "Enter Quantity:\n" +
                "Cash inserted successfully.\n" +
                "change is 100.0\n" +
                "1 100.0 dollar note/coin was sent out\n" +
                "You have enough to checkout. Checkout now? (Y|N)\n" +
                "Change of $100.00 in AUD received.\n" +
                "Thank you for your purchase, come back again!\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";




        assertEquals(out, getOutput());
    }

    @Test
    public void PurchaseTooMuch(){
        String in = "";
        in += "1\n"; //enter buying menu
        in += "1\n"; // buy first item
        in += "11\n"; // buy it 3 times
        in += "y\n";
        in += "1\n";
        in += "3\n";
        in += "n\n";
        in += "3\n";

        setInput(in);

        ci.run();

        String out = "";
        out += "===========Welcome to vending machine!================\n" +
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
                "Enter your options:\n" +
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
                "Sorry, we do not have enough stock to accommodate your request.\n" +
                "Continue Shopping? (Y|N)\n" +
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



        assertEquals(out, getOutput());
    }

    @Test
        public void PurchaseToPaymentUSD() {
            String in = "";
            in += "1\n"; //enter buying menu
            in += "1\n"; // buy first item
            in += "3\n";
            in += "n\n";
            in += "2\n";      //Get into Shopping cart interface
            in += "2\n";
            in += "USD\n"; // choose USD
            in += "10\n";
            in += "4\n";
            in += "y\n";
            in += "3\n"; //exit
            setInput(in);

            ci.run();

            String out = "";
            out += "===========Welcome to vending machine!================\n" +
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
                "Enter your options:\n" +
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
                "------------------Shopping Cart------------------\n" +
                "ID   Items               Type      Price     Quantity  \n" +
                "1    Pepsi               DRINK     5.0       3         \n" +
                "Total Price: $ 15.0\n" +
                "\n" +
                "1. Delete Items\n" +
                "2. Checkout\n" +
                "How would you like to pay?\n" +
                "AUD\n" +
                "USD\n" +
                "NZD\n" +
                "SGD\n" +
                "CAD\n" +
                "Enter your selection: \n" +
                "You need to pay: $10.34 in USD\n" +
                "You have paid: $0.00 in USD\n" +
                "ID   Items               Type      \n" +
                "------------------------------------------------------\n" +
                "1    10c                 COIN      0.1       10        \n" +
                "2    20c                 COIN      0.2       10        \n" +
                "3    50c                 COIN      0.5       10        \n" +
                "4    $1                  COIN      1.0       10        \n" +
                "5    $2                  COIN      2.0       10        \n" +
                "6    $5                  NOTE      5.0       10        \n" +
                "7    $10                 NOTE      10.0      10        \n" +
                "8    $20                 NOTE      20.0      5         \n" +
                "9    $50                 NOTE      50.0      2         \n" +
                "10   $100                NOTE      100.0     1         \n" +
                "======================================================\n" +
                "\n" +
                "Return to cart: 0 \tOR\n" +
                "Enter ID: \n" +
                "Enter Quantity:\n" +
                "Cash inserted successfully.\n" +
                "change is 389.6\n" +
                "3 100.0 dollar note/coin was sent out\n" +
                "1 50.0 dollar note/coin was sent out\n" +
                "1 20.0 dollar note/coin was sent out\n" +
                "1 10.0 dollar note/coin was sent out\n" +
                "1 5.0 dollar note/coin was sent out\n" +
                "2 2.0 dollar note/coin was sent out\n" +
                "1 0.5 dollar note/coin was sent out\n" +
                "1 0.1 dollar note/coin was sent out\n" +
                "You have enough to checkout. Checkout now? (Y|N)\n" +
                "Change of $389.66 in USD received.\n" +
                "Thank you for your purchase, come back again!\n" +
                "Options:\n" +
                "1. Purchase\n" +
                "2. Shopping Cart\n" +
                "3. Quit\n" +
                "Enter your options:\n" +
                "Thank you!\n";

        assertEquals(out, getOutput());
    }


    @Test
    public void PurchaseIDTooHigh() {
        String in = "";
        in += "1\n"; //enter buying menu
        in += "20\n"; // buy first item
        in += "3\n";
        in += "n\n";
        in += "3\n"; //exit
        setInput(in);

        ci.run();

        String out = "";
        out+= "===========Welcome to vending machine!================\n" +
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
                "Enter your options:\n" +
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

        assertEquals(out, getOutput());

    }

    @Test
    public void PurchaseIDTooLow() {
        String in = "";
        in += "1\n"; //enter buying menu
        in += "0\n"; // buy first item
        in += "3\n";
        in += "n\n";
        in += "3\n"; //exit
        setInput(in);

        ci.run();

        String out = "";
        out+= "===========Welcome to vending machine!================\n" +
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
                "Enter your options:\n" +
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

        assertEquals(out, getOutput());

    }


    @Test
    public void PurchaseQuantityTooLow() {
        String in = "";
        in += "1\n"; //enter buying menu
        in += "1\n"; // buy first item
        in += "0\n";
        in += "n\n";
        in += "3\n"; //exit
        setInput(in);

        ci.run();

        String out = "";
        out += "===========Welcome to vending machine!================\n" +
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
                "Enter your options:\n" +
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

        assertEquals(out, getOutput());
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
