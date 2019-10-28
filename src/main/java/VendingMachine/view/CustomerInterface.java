package VendingMachine.view;


import VendingMachine.ConfigReader;
import VendingMachine.CurrencyConverter;
import VendingMachine.User.Customer;
import VendingMachine.User.CustomerImpl;
import VendingMachine.model.*;

import java.util.Scanner;


public class CustomerInterface implements CommandLineInterface {
    private VendingMachine vd;
    private ShoppingCart cart;
    private Customer customer;
    private CurrencyConverter converter = new CurrencyConverter(ConfigReader.readRateConfigs("src/main/resources/config.json"));

    public CustomerInterface(VendingMachine vendingMachine) {
        this.vd = vendingMachine;
        this.customer = new CustomerImpl();
        this.cart = customer.getCart();
        commandLine();
    }

    public void commandLine(){
        System.out.println("===========Welcome to vending machine!================\n");
        printAllFood();
        Scanner sc = new Scanner(System.in);
        while(true){
            printMainMenu();
            String input = sc.next();
            switch (input){
                case "1":
                    purchaseInterface();
                    break;
                case "2":
                    shoppingCartInterface();
                    break;
                case "3":
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
            }
        }
    }

    public void purchaseInterface(){
        boolean purchasing = true;
        Scanner purchase_sc = new Scanner(System.in);
        while(purchasing) {
            printAllFood();
            System.out.println("Enter ID:");
            int id = Integer.parseInt(purchase_sc.next());
            System.out.println("Enter Quantity:");
            int qua = Integer.parseInt(purchase_sc.next());

            InventoryItem target = vd.getInventory().getInventoryItemByFoodId(id);
            cart.addToCart(target, qua);

            System.out.println("Continue Shopping? (Y|N)");
            String answer = purchase_sc.next().toUpperCase();
            if(answer.equals("N")){
                purchasing=false;
            }
        }
    }

    public void shoppingCartInterface(){
        System.out.println(cart.toString());
        System.out.println("1. Delete Items");
        System.out.println("2. Checkout");
        Scanner cart_sc = new Scanner(System.in);
        String option = cart_sc.next();
        switch (option){
            case "1":
                boolean deleting = true;
                Scanner deleting_sc = new Scanner(System.in);
                while(deleting){
                    System.out.println(cart.toString());
                    System.out.println("Enter ID:");
                    int id = Integer.parseInt(deleting_sc.next());
                    System.out.println("Enter Quantity:");
                    int qua = Integer.parseInt(deleting_sc.next());

                    InventoryItem target = cart.getInventoryItemByFoodId(id);
                    cart.removeFromCart(target, qua);

                    InventoryItem target2 = vd.getInventory().getInventoryItemByFoodId(id);
                    target2.addQuantity(qua);

                    System.out.println("Continue Deleting? (Y|N)");
                    String answer = deleting_sc.next().toUpperCase();
                    if(answer.equals("N")){
                        deleting = false;
                    }
                }
                break;
            case "2":
                printCurrencyList();
                Scanner currency_sc = new Scanner(System.in);
                String selection = currency_sc.next().replace(" ","").toUpperCase();  //delete any white space
                double amountDue = converter.convertCurrency("USD",selection,cart.getTotalPrice());

                Payment payment = new Payment(customer, amountDue, selection);
                if(paymentInterface(payment)) {
                    cart.resetCart();
                    payment.returnChange(true);
                    System.out.println("Thank you for your purchase, come back again!");
                    break;
                } else {
                    payment.returnChange(false);
                    System.out.println("Your change has been returned.");
                    break;
                }
        }
    }

    public void printAllFood(){
        System.out.println(vd.foodToString());
    }

    public void printAllCash(){
        System.out.println(vd.cashToString());
    }

    public void printMainMenu(){
        System.out.println("Options:");
        System.out.println("1. Purchase");
        System.out.println("2. Shopping Cart");
        System.out.println("3. Quit");
        System.out.println("Enter your options:");
    }

    public void printCurrencyList(){
        System.out.println("How would you like to pay?");
        System.out.println("USD");
        System.out.println("AUD");
        System.out.println("CNY");
        System.out.println("JPY");
        System.out.println("CAD");
        System.out.println("Enter your selection: ");
    }

    public boolean paymentInterface(Payment payment) {
        boolean success = false;
        Scanner payment_sc = new Scanner(System.in);
        while(true) {
            payment.printStatus();
            printAllCash();
            System.out.println("Return to cart: 0 \tOR");
            System.out.println("Enter ID: ");
            int id = Integer.parseInt(payment_sc.next());
            if(id == 0) {
                break;
            }
            System.out.println("Enter Quantity:");
            int qua = Integer.parseInt(payment_sc.next());

            CofferDenomination target = vd.getCoffer().getDenominationByCashId(id);
            payment.makePayment(target, qua);
            if(payment.change() >= 0) {
                System.out.println("You have enough to checkout. Checkout now? (Y|N)");
                String answer = payment_sc.next().toUpperCase();
                if(answer.equals("Y")){
                    success = true;
                    break;
                }
            }
        }
        return success;
    }
}