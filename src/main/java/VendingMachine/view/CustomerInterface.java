package VendingMachine.view;


import java.util.Scanner;

import VendingMachine.User.Customer;
import VendingMachine.User.CustomerImpl;
import VendingMachine.model.*;
import VendingMachine.CurrencyConverter;
import VendingMachine.ConfigReader;


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
        printVendingMachine();
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
            System.out.println("Enter ID:");
            int id = Integer.parseInt(purchase_sc.next());
            System.out.println("Enter Quantity:");
            int qua = Integer.parseInt(purchase_sc.next());

            InventoryItem target = null;
            for(InventoryItem item: vd.getInventory().getInventoryItems()){
                if(item.getFood().getId()==id){
                    target = item;
                    break;
                }
            }
            if(target==null){
                System.out.println("Invalid ID");
            }else {
                cart.addToCart(target.clone(qua));
            }

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
                    System.out.println("Enter ID:");
                    int id = Integer.parseInt(deleting_sc.next());
                    System.out.println("Enter Quantity:");
                    int qua = Integer.parseInt(deleting_sc.next());

                    //The following part can be put into a method - findFood
                    InventoryItem target = null;
                    for(InventoryItem item:cart.getCart()){
                        if(item.getFood().getId()==id){
                            target = item;
                            break;
                        }
                    }
                    if(target==null){
                        System.out.println("Invalid ID");
                    }else if(qua>target.getQuantity()){
                        System.out.println("Not enough in cart");
                    } else{
                        cart.removeFromCart(target,qua);
                        System.out.println("Deleted");
                        System.out.println("\n"+cart.toString());
                    }

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
                double amount = converter.convertCurrency("USD",selection,cart.getTotalPrice());
                System.out.printf("You need to pay: %f in %s\n", amount, selection);
                System.out.println("Checkout? (Y|N)");
                Payment payment = new Payment(customer,cart.getTotalPrice());
                String answer = currency_sc.next().toUpperCase();
                if(answer.equals("Y")){
                    //do customer pay here
                    System.out.println("Time to pay");
                    conversation(payment);
                    for(InventoryItem item: cart.getCart()){
                        vd.getInventory().removeFoodFromInventory(item.getFood().getId(),item.getQuantity());
                    }
                    System.out.println("Thank you for your purchasing");
                    System.exit(0);
                }else if(answer.equals("N")){
                    //do nothing
                }
                break;
        }
    }

    public void printVendingMachine(){
        System.out.println(vd.toString());
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
    public void conversation(Payment payment) {
        payment.setPaid(new Money());
        Scanner scan = new Scanner(System.in);
        System.out.println("How is this being paid?");
        double moneyGiven = 0;
        String temp;
        while (moneyGiven != payment.getPaymentAmount()) {
            System.out.println("Please enter the number of 10 cent coins that are to be entered.");
            temp = scan.nextLine();
            try {
                payment.getPaid().tenCents = Integer.parseInt(temp);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            System.out.println("Please enter the number of 20 cent coins that are to be entered.");
            temp = scan.nextLine();
            try {
                payment.getPaid().twentyCents = Integer.parseInt(temp);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            System.out.println("Please enter the number of 50 cent coins that are to be entered.");
            temp = scan.nextLine();
            try {
                payment.getPaid().fiftyCents = Integer.parseInt(temp);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            System.out.println("Please enter the number of 1 dollar coins that are to be entered.");
            temp = scan.nextLine();
            try {
                payment.getPaid().one = Integer.parseInt(temp);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            System.out.println("Please enter the number of 2 dollar coins that are to be entered.");
            temp = scan.nextLine();
            try {
                payment.getPaid().two = Integer.parseInt(temp);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            System.out.println("Please enter the number of 5 dollar notes that are to be entered.");
            temp = scan.nextLine();
            try {
                payment.getPaid().five = Integer.parseInt(temp);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            System.out.println("Please enter the number of 10 dollar notes that are to be entered.");
            temp = scan.nextLine();
            try {
                payment.getPaid().ten = Integer.parseInt(temp);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            System.out.println("Please enter the number of twemty dollar notes that are to be entered.");
            temp = scan.nextLine();
            try {
                payment.getPaid().twenty = Integer.parseInt(temp);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            moneyGiven = payment.getTotal();
            System.out.println("Amount being paid is : " + moneyGiven + "." + " Amount expected is " + payment.getPaymentAmount());
        }
        //the money has been given.
    }
}
