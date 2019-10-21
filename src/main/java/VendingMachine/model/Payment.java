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
     * All status of this payment will be set up once it is created, including payment succeed or fail, and how much
     * chager
     *
     * @param customer      the customer who is using vending machine
     * @param paymentAmount how much money user pay for the vending machine
     */
    public Payment(Customer customer, double paymentAmount) {

        this.customer = customer;
        this.paymentAmount = paymentAmount;

        this.totalPrice = customer.getCart().getTotalPrice();

        //Positive charge means that customer pays enough
        this.change = paymentAmount - totalPrice;

        //if customer pay enough, this payment is success, or otherwise this payment will be declined.
        if (change > 0) {
            this.success = true;
        } else {
            this.success = false;
        }
    }

    /**
     * Determine if this payment is success or fail
     *
     * @return the status of payment - true: payment succeed; -false: paymeny fail
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Get the charge of payment. If charge is positive, it means payment succeed. If it is negative, it means fail
     *
     * @return the charge
     */
    public double getChange() {
        return change;
    }

    /**
     * Generate String to show receipt for customer to read
     *
     * @return the receipt of this payment
     */
    public String receipt() {
        String receipt = "";
        if (success) {
            receipt += "Payment succeed";   //extend more later
        } else {
            receipt += "Payment fail";      //extend more later
        }
        return receipt;
    }
    // public Money getMoney(){
    //  return this.paid;
    //}


//Oscar! Your code is here!
    public Money getPaid(){
        return this.paid;
    }
    public double getPaymentAmount(){
        return this.paymentAmount;
    }
    public void setPaid(Money paid){
        this.paid = paid;
    }
    public double getTotal(){
        return (double)(paid.tenCents * 0.1 + paid.twentyCents * 0.2 + paid.fiftyCents * 0.5 + paid.one + paid.two * 2 + paid.five * 5 + paid.ten * 10 + paid.twenty * 20);
    }
}