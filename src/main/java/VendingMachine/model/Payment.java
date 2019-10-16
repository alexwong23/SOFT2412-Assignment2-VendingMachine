package VendingMachine.model;

import VendingMachine.User.Customer;

public class Payment {

    private Customer customer;
    private double paymentAmount;
    private double totalPrice;
    private double charge;
    private boolean success;

    /**
     *
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
     *
     * @return the status of payment - true: payment succeed; -false: paymeny fail
     */
    public boolean isSuccess(){
        return success;
    }

    /**
     *
     * @return the charge
     */
    public double getCharge(){
        return charge;
    }

    /**
     *
     * @return the receipt of this payment
     */
    public String receipt(){
        String receipt="";
        if(success){
            receipt+="Payment succeed\n";
        }else{
            receipt+="Payment fail\n";
        }
        return receipt;
    }
}
