package VendingMachine.view;

import VendingMachine.model.Food;
import VendingMachine.model.ShoppingCart;
import VendingMachine.model.VendingMachine;

import java.util.Scanner;

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
        printMainMenu();

        // Code that reads user inputs goes here
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String input = sc.next();
            switch (input){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
            }

        }
        System.out.println("Enter Id to make purchase:");

        int id = Integer.parseInt(sc.next());
        System.out.println("Pleas enter quantity");
        int qua = Integer.parseInt(sc.next());
        // Code that outputs data to users goes here
    }

    public void printVendingMachine(){
        System.out.println("===========Welcome to vending machine!===========");
        System.out.printf("%-5s%-20s%-10s%-10s%-10s\n","ID","Items","Type","Price","Qua");
        System.out.println("-------------------------------------------------");
        for (int i=0; i<vd.getAllFood().size(); i++) {
            int itemId = i+1;
            System.out.printf("%-5d%s\n",itemId,vd.getAllFood().get(i).getDisplayString());
        }
        System.out.println("=================================================");
    }

    public void printMainMenu(){
        System.out.println("Options: ");
        System.out.println("1. Purchase");
        System.out.println("2. Shopping Cart");
        System.out.println();
        System.out.println("3. Quit");
    }

    public void
}