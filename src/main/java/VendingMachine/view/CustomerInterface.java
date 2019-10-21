package VendingMachine.view;

import java.sql.SQLOutput;
import java.util.Scanner;

import VendingMachine.User.Customer;
import VendingMachine.User.CustomerImpl;
import VendingMachine.model.Food;
import VendingMachine.model.Payment;
import VendingMachine.model.ShoppingCart;
import VendingMachine.model.VendingMachine;


public class CustomerInterface implements CommandLineInterface {
    private VendingMachine vd;
    private ShoppingCart cart;
    private Customer customer;

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

    public void printVendingMachine(){
        System.out.println("===========Welcome to vending machine!===========");
        System.out.printf("%-5s%-20s%-10s%-10s%-10s\n","ID","Items","Type","Price","Qua");
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

    public void purchaseInterface(){
        boolean purchasing = true;
        Scanner sc = new Scanner(System.in);
        while(purchasing) {
            System.out.println("Enter ID:");
            int id = Integer.parseInt(sc.next());
            System.out.println("Enter Quantity:");
            int qua = Integer.parseInt(sc.next());

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
            String answer = sc.next().toUpperCase();
            if(answer.equals("N")){
                purchasing=false;
            }
        }
    }

    public void shoppingCartInterface(){
        System.out.println(cart.toString());
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Delete Items");
        System.out.println("2. Checkout");

        String option = sc.next();
        switch (option){
            case "1":
                boolean deleting = true;
                while(deleting){
                    Scanner deleting_sc = new Scanner(System.in);
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
                Payment payment = new Payment(customer,cart.getTotalPrice());
                break;
        }
    }
}