package VendingMachine.model;

import VendingMachine.User.Customer;

import java.util.Scanner;

public class Payment {

    private Customer customer;
    private double paymentAmount;
    private double totalPrice;
    private double change;
    private boolean success;
    private Money paid;

//    Customer can:
//
//    Using coins(10c, 20c, 50c, $1, $2) / notes($5, $10, $20)
//    If not enough, prompt to enter remainder amount or cancel
//    Upon success, customer receives product and change.
//    Product Stock will be updated accordingly


    /**
     *  All status of this payment will be set up once it is created, including payment succeed or fail, and how much
     *  chager
     * @param customer  the customer who is using vending machine
     * @param paymentAmount how much money user pay for the vending machine
     */
    public Payment(Customer customer, double paymentAmount){
        this.paid = new Money();
        Scanner scan = new Scanner(System.in);
        System.out.println("How is this being paid? ");
        double moneyGiven = 0;
        String temp;
        while(moneyGiven< paymentAmount){
            System.out.println("Please enter the number of 10 cent coins that are to be entered.");
            temp = scan.nextLine();
            try {
                paid.tenCents = Integer.parseInt(temp);
            }
            catch(Exception e ){e.printStackTrace();}
            System.out.println("Please enter the number of 20 cent coins that are to be entered.");
            temp = scan.nextLine();
            try {
                paid.twentyCents = Integer.parseInt(temp);
            }
            catch(Exception e ){e.printStackTrace();}
            System.out.println("Please enter the number of 50 cent coins that are to be entered.");
            temp = scan.nextLine();
            try {
                paid.fiftyCents = Integer.parseInt(temp);
            }
            catch(Exception e ){e.printStackTrace();}
            System.out.println("Please enter the number of 1 dollar coins that are to be entered.");
            temp = scan.nextLine();
            try {
                paid.one = Integer.parseInt(temp);
            }
            catch(Exception e ){e.printStackTrace();}
            System.out.println("Please enter the number of 2 dollar coins that are to be entered.");
            temp = scan.nextLine();
            try {
                paid.two = Integer.parseInt(temp);
            }
            catch(Exception e ){e.printStackTrace();}
            System.out.println("Please enter the number of 5 dollar notes that are to be entered.");
            temp = scan.nextLine();
            try {
                paid.five = Integer.parseInt(temp);
            }
            catch(Exception e ){e.printStackTrace();}
            System.out.println("Please enter the number of 10 dollar notes that are to be entered.");
            temp = scan.nextLine();
            try {
                paid.ten = Integer.parseInt(temp);
            }
            catch(Exception e ){e.printStackTrace();}
            System.out.println("Please enter the number of twemty dollar notes that are to be entered.");
            temp = scan.nextLine();
            try {
                paid.twenty = Integer.parseInt(temp);
            }
            catch(Exception e ){e.printStackTrace();}
            moneyGiven = paid.tenCents*0.1+paid.twentyCents*0.2+paid.fiftyCents*0.5+paid.one+paid.two*2+paid.five*5+paid.ten*10+paid.twenty*20;
            System.out.println("Amount being paid is : "+ moneyGiven"."+ " Amount expected is "+ paymentAmount);
        }
        this.customer = customer;
        this.paymentAmount = paymentAmount;

        this.totalPrice = customer.getCart().getTotalPrice();

        //Positive charge means that customer pays enough
        this.change = paymentAmount-totalPrice;

        //if customer pay enough, this payment is success, or otherwise this payment will be declined.
        if(change>0) {
            this.success = true;
        }else{
            this.success = false;
        }
    }

    /**
     * Determine if this payment is success or fail
     * @return the status of payment - true: payment succeed; -false: paymeny fail
     */
    public boolean isSuccess(){
        return success;
    }

    /**
     *  Get the charge of payment. If charge is positive, it means payment succeed. If it is negative, it means fail
     * @return the charge
     */
    public double getChange(){
        return change;
    }

    /**
     *  Generate String to show receipt for customer to read
     * @return the receipt of this payment
     */
    public String receipt(){
        String receipt="";
        if(success){
            receipt+="Payment succeed\n";   //extend more later
        }else{
            receipt+="Payment fail\n";      //extend more later
        }
        return receipt;
    }
    public Money getMoney(){
        return this.paid;
    }
}
