package VendingMachine.view;


import VendingMachine.ConfigReader;
import VendingMachine.CurrencyConverter;
import VendingMachine.User.Customer;
import VendingMachine.User.CustomerImpl;
import VendingMachine.model.*;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Scanner;


public class CustomerInterface implements CommandLineInterface {

    //The two variable below are responsible for secrete channel for staff
    private String secret="";
    private String staff="STAFF";   //user input the string can get in staff interface

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
    }

    public void run(){
        System.out.println("===========Welcome to vending machine!================");
        printAllFood();
        Scanner sc = new Scanner(System.in);
        boolean shopping = true;
        while(shopping){
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
                    shopping=false;
                    break;
                default:
                    notifier(input);
            }
        }
        sc.close();
    }

    public void purchaseInterface(){
        boolean purchasing = true;
        Scanner purchase_sc = new Scanner(System.in);
        while(purchasing) {
            printAllFood();

            System.out.println("Enter ID:");
            String idString =purchase_sc.next();
            notifier(idString);

            System.out.println("Enter Quantity:");
            String quaString = purchase_sc.next();
            notifier(quaString);

            try {
                int id = Integer.parseInt(idString);
                int qua = Integer.parseInt(quaString);
                InventoryItem target = vd.getInventory().getInventoryItemByFoodId(id);
                cart.addToCart(target, qua);
            }catch(Exception e){
                System.out.println("Invalid ID or Quantity");
            }

            System.out.println("Continue Shopping? (Y|N)");

            String answer = purchase_sc.next().toUpperCase();

            notifier(answer);

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
                while(deleting){
                    System.out.println(cart.toString());    //print shoppingcart

                    System.out.println("Enter ID:");
                    String idString = cart_sc.next();
                    notifier(idString);

                    System.out.println("Enter Quantity:");
                    String quaString = cart_sc.next();
                    notifier(quaString);

                    try {
                        int id = Integer.parseInt(idString);
                        int qua = Integer.parseInt(quaString);

                        InventoryItem target = cart.getInventoryItemByFoodId(id);
                        cart.removeFromCart(target, qua);

                        InventoryItem target2 = vd.getInventory().getInventoryItemByFoodId(id);
                        target2.addQuantity(qua);

                    }catch (Exception e){
                        System.out.println("Invalid ID or Quantity");
                    }

                    System.out.println("Continue Deleting? (Y|N)");
                    String answer = cart_sc.next().toUpperCase();

                    notifier(answer);

                    if(answer.equals("N")){
                        deleting = false;
                    }
                }
                break;
            case "2":
                printCurrencyList();

                String selection = cart_sc.next().replace(" ","").toUpperCase();  //delete any white space

                notifier(selection);

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
            default:
                notifier(option);
        }
    }

    public void staffInterface(){
        Scanner scanner_st = new Scanner(System.in);
        System.out.println("Enter your staff id:");
        String id = scanner_st.next();
        if (StaffInterface.StaffIDCheck(id)) {
            new StaffInterface(vd).run();
        } else {
            System.out.println("invalid staff id");
        }
    }

    public void printAllFood(){
        System.out.print(vd.foodToString());
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
            int id = -1;
            System.out.println("Return to cart: 0 \tOR");
            System.out.println("Enter ID: ");

            String idString = payment_sc.next();

            notifier(idString); //leave this line behind idString

            try{
                id = Integer.parseInt(idString);
            }catch(Exception e){
                System.out.println("did not enter an ID, nor did you choose to return.");
            }
            if(id == 0) {
                break;
            }
            int qua = 0;
            if(id>0){
                String quaString = payment_sc.next();

                notifier(quaString);    //leave this line behind quaString

                System.out.println("Enter Quantity:");

                try {
                    qua = Integer.parseInt(quaString);
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
                    System.out.println("You have enough to checkout. Checkout now? (Y|N)");
                    String answer = payment_sc.next().toUpperCase();

                    notifier(answer);   //leave this line after answer

                    if(answer.equals("Y")){
                        success = true;
                        break;
                    }
                }else{

                    System.out.println("Your money will now be ejected");
                    vd.getCoffer().payOut(payment.getAmountPaid());
                    payment.setAmountPaid(0);
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

    /**
     *
     * @param input the input from user
     */
    public void notifier(String input){
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException err) {
            secret+=input;
        } catch(NullPointerException err) {
            System.out.println("Null Pointer");
        }

        if(secret.toUpperCase().equals(staff)){
            staffInterface();
        }
    }

    // for testing
    public ShoppingCart getShoppingCart(){
        return this.cart;
    }
}
