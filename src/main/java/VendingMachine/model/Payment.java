package VendingMachine.model;

import VendingMachine.User.Customer;

public class Payment {

    private Customer customer;
    private double paymentAmount;
    private double totalPrice;
    private double charge;
    private boolean success;

    /**
     *  All status of this payment will be set up once it is created, including payment succeed or fail, and how much
     *  chager
     * @param customer  the customer who is using vending machine
     * @param paymentAmount how much money user pay for the vending machine
     */
    public Payment(Customer customer, double paymentAmount){
        this.customer = customer;
        this.paymentAmount = paymentAmount;

        this.totalPrice = customer.getCart().getTotalPrice();

        //Positive charge means that customer pays enough
        this.charge = paymentAmount-totalPrice;

        //if customer pay enough, this payment is success, or otherwise this payment will be declined.
        if(charge>0) {
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
    public double getCharge(){
        return charge;
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
}
