package VendingMachine.view;


import java.util.Scanner;

import VendingMachine.User.Customer;
import VendingMachine.User.CustomerImpl;
import VendingMachine.model.Food;
import VendingMachine.model.Payment;
import VendingMachine.model.ShoppingCart;
import VendingMachine.model.VendingMachine;
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

            Food target = null;
            for(Food food: vd.getAllFood()){
                if(food.getId()==id){
                    target = food;
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
                    Food target = null;
                    for(Food food:cart.getCart()){
                        if(food.getId()==id){
                            target = food;
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
                String selection = currency_sc.next().replace(" ","");  //delete any white space
                double amount = converter.convertCurrency("USD",selection,cart.getTotalPrice());
                System.out.printf("You need to pay: %f in %s\n", amount, selection);
                System.out.println("Checkout? (Y|N)");
                Payment payment = new Payment(customer,cart.getTotalPrice());
                String answer = currency_sc.next();
                if(answer.equals("Y")){
                    //do customer pay here
                    System.out.println("Thank you for your purchasing");
                    System.exit(0);
                }else if(answer.equals("N")){
                    //do nothing
                }
                break;
        }
    }

    public void printVendingMachine(){
        System.out.println("===========Welcome to vending machine!===========");
        System.out.printf("%-5s%-20s%-10s%-10s%-10s\n","ID","Items","Type","Price","Quantity");
        System.out.println("-------------------------------------------------");
        for (Food food :vd.getAllFood()) {
            System.out.printf("%s\n",food.getDisplayString());
        }
        System.out.println("=================================================");
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
}