package VendingMachine.view;

import java.sql.SQLOutput;
import java.util.Scanner;

import VendingMachine.model.Food;
import VendingMachine.model.ShoppingCart;
import VendingMachine.model.VendingMachine;


public class CustomerInterface implements CommandLineInterface {
    private VendingMachine vd;
    private ShoppingCart cart;

    public CustomerInterface(VendingMachine vendingMachine) {
        this.vd = vendingMachine;
        this.cart = new ShoppingCart();
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
                    break;
                case "3":
                    System.exit(1);
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
        //cart.report();
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

                    //cart.removeFromCart();

                    System.out.println("Continue Deleting? (Y|N)");
                    String answer = deleting_sc.next().toUpperCase();
                    if(answer.equals("N")){
                        deleting = false;
                    }
                }
                break;
            case "2":

                break;
        }
    }
}