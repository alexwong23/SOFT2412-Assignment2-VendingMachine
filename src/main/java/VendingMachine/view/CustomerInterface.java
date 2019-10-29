package VendingMachine.view;


import VendingMachine.ConfigReader;
import VendingMachine.CurrencyConverter;
import VendingMachine.User.Customer;
import VendingMachine.User.CustomerImpl;
import VendingMachine.model.*;

import java.util.ArrayList;
import java.util.Scanner;


public class CustomerInterface implements CommandLineInterface {
    private Records record;
    private VendingMachine vd;
    private ShoppingCart cart;
    private Customer customer;
    private CurrencyConverter converter = new CurrencyConverter(ConfigReader.readRateConfigs("src/main/resources/config.json"));

    public CustomerInterface(VendingMachine vendingMachine) {
        this.record = new Records();
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

    public void purchaseLoop(boolean purchasing, Scanner purchase_sc){
        while(purchasing) {
            printAllFood();
            int id = -1;
            int qua = -1;
            try {
                System.out.println("Enter ID:");
                id = Integer.parseInt(purchase_sc.next());
                if(id<1||id>7){
                    System.out.println("No ID found.");
                    id = -1;
                }

            } catch (Exception e) {
                System.out.println("did not enter an ID. Please choose again, or stop shopping.");
            }
            if(id!=-1){
                try {
                    System.out.println("Enter Quantity:");
                    qua = Integer.parseInt(purchase_sc.next());

                } catch (Exception e) {
                    System.out.println("did not enter an amount. Please choose again or stop shopping.");
                }
            }
            if(qua>-1 &&id>-1){
                InventoryItem target = vd.getInventory().getInventoryItemByFoodId(id);
                cart.addToCart(target, qua);

            }
            System.out.println("Continue Shopping? (Y|N)");
            String answer = purchase_sc.next().toUpperCase();
            if(answer.equals("N")){
                purchasing=false;
            }
        }
    }

    public void purchaseInterface(){
        boolean purchasing = true;
        Scanner purchase_sc = new Scanner(System.in);
        purchaseLoop(purchasing, purchase_sc);

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
                purchaseLoop(deleting, deleting_sc);
//                while(deleting){
//                    System.out.println(cart.toString());
//                    System.out.println("Enter ID:");
//                    int id = Integer.parseInt(deleting_sc.next());
//                    System.out.println("Enter Quantity:");
//                    int qua = Integer.parseInt(deleting_sc.next());
//
//                    InventoryItem target = cart.getInventoryItemByFoodId(id);
//                    cart.removeFromCart(target, qua);
//
//                    InventoryItem target2 = vd.getInventory().getInventoryItemByFoodId(id);
//                    target2.addQuantity(qua);
//
//                    System.out.println("Continue Deleting? (Y|N)");
//                    String answer = deleting_sc.next().toUpperCase();
//                    if(answer.equals("N")){
//                        deleting = false;
//                    }
//                }
                break;
            case "2":
                printCurrencyList();
                Scanner currency_sc = new Scanner(System.in);
                try{
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
                }catch(Exception e){
                    System.out.println("invalid currency");
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

//    public void conversation(Payment payment) {
//        payment.setPaid(new Money());
//        Scanner scan = new Scanner(System.in);
//        System.out.println("How is this being paid?");
//        double moneyGiven = 0;
//        String temp;
//        boolean successful_purchase = false;
//        while (successful_purchase) {
//            System.out.println("Please enter the number of 10 cent coins that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().tenCents = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//            System.out.println("Please enter the number of 20 cent coins that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().twentyCents = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//            System.out.println("Please enter the number of 50 cent coins that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().fiftyCents = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//            System.out.println("Please enter the number of 1 dollar coins that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().one = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//            System.out.println("Please enter the number of 2 dollar coins that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().two = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//            System.out.println("Please enter the number of 5 dollar notes that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().five = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//            System.out.println("Please enter the number of 10 dollar notes that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().ten = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();


    public boolean paymentInterface(Payment payment) {
        boolean success = false;
        Scanner payment_sc = new Scanner(System.in);
        while(true) {
            payment.printStatus();
            printAllCash();
            int id = -1;
            System.out.println("Return to cart: 0 \tOR");
            System.out.println("Enter ID: ");

            try{
                id = Integer.parseInt(payment_sc.next());
            }catch(Exception e){
                System.out.println("did not enter an ID, nor did you choose to return.");
            }
            if(id == 0) {
                break;
            }
            int qua = 0;
            if(id>0){
                System.out.println("Enter Quantity:");

                try {
                    qua = Integer.parseInt(payment_sc.next());
                } catch (Exception e) {
                    System.out.println("did not enter an amount. Please choose again, or checkout.");
                }
            }
//            int qua = Integer.parseInt(payment_sc.next());
            if(qua>0){
                CofferDenomination target = vd.getCoffer().getDenominationByCashId(id);
                payment.makePayment(target, qua);
            }
            if(payment.change() >= 0) {
                System.out.println("change is "+ payment.change());
                boolean paid = vd.getCoffer().payOut(payment.change());
                if(paid==true){
                    //money traklen out from ccustomer.

                    //skip that for now
//                    record.success(0,(ArrayList) cart.getCart());
                }else{
//                    record.fail(0, (ArrayList) cart.getCart());
                }
                System.out.println("You have enough to checkout. Checkout now? (Y|N)");
                String answer = payment_sc.next().toUpperCase();
                if(answer.equals("Y")){
                    success = true;
                    break;
                }
            }
//            System.out.println("Please enter the number of fifty dollar notes that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().fifty = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//            System.out.println("Please enter the number of hundred dollar notes that are to be entered.");
//            temp = scan.nextLine();
//            try {
//                payment.getPaid().hundred = Integer.parseInt(temp);
//            } catch (Exception e) {
////                e.printStackTrace();
//            }
//            moneyGiven = payment.getTotal();
//            if(moneyGiven>=payment.getPaymentAmount()){ // see if the money given is equla to or more than the amoutn that has to be paid
//                //do change calculations
//                double vdMoney = vd.totalMoney();
//                if(vdMoney==moneyGiven){
//                    successful_purchase=true;
//                }else if(vdMoney>moneyGiven){
//
//                    successful_purchase = true;
//                }else{
//                    successful_purchase = false;
//                }
//            }
//            System.out.println("Amount being paid is : " + moneyGiven + "." + " Amount expected is " + payment.getPaymentAmount());
        }
        return success;
    }
}
