package VendingMachine.model;

import VendingMachine.User.Customer;

public class Payment {

    private Customer customer;
    private double amountDue;
    private double amountPaid;
    private String currency;

//    Customer can:
//
//    Using coins(10c, 20c, 50c, $1, $2) / notes($5, $10, $20)
//    If not enough, prompt to enter remainder amount or cancel
//    Upon success, customer receives product and change.
//    Product Stock will be updated accordingly

    /**
     * All status of this payment will be set up once it is created, including payment succeed or fail, and how much
     * chager
     *
     * @param customer      the customer who is using vending machine
     * @param amountDue how much money user pay for the vending machine
     * @param currency currency to pay in
     */
    public Payment(Customer customer, double amountDue, String currency) {
        this.customer = customer;
        this.amountDue = amountDue;
        this.currency = currency;
        this.amountPaid = 0;
    }

    public void resetPayment() {
        this.amountDue = 0;
        this.currency = "";
        this.amountPaid = 0;
    }
    public double change() { return this.amountPaid - this.amountDue; }

    public void makePayment(CofferDenomination e, int quantity) {
        if(e==null){
            System.out.println("Sorry, invalid ID provided.");
        }else if(quantity <= 0) {
            System.out.println("Sorry, invalid quantity provided.");
        } else {
            e.addQuantity(quantity); // add original quantity
            this.amountPaid += e.getCash().getValue() * quantity;
            System.out.println("Cash inserted successfully.");
        }
    }

    // return change greedily!!! INCOMPLETE
    // just do the algorithm here
    public void returnChange(boolean paymentSuccessful){
        if(paymentSuccessful) {
            System.out.printf("Change of $%.2f in %s received.\n", change(), this.currency);
        } else {
            System.out.printf("Change of $%.2f in %s received.\n", this.amountPaid, this.currency);
        }
        resetPayment();
    }

    public void printStatus(){
        System.out.printf("You need to pay: $%.2f in %s\n", this.amountDue, this.currency);
        System.out.printf("You have paid: $%.2f in %s\n", this.amountPaid, this.currency);
    }
}
