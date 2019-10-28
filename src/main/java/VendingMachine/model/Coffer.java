package VendingMachine.model;

import VendingMachine.config.CashConfig;

import java.util.ArrayList;

public class Coffer {

    private ArrayList<CofferDenomination> cofferDenominations = new ArrayList<>();

    private static int MAX_QUANTITY = 20;

    Coffer(ArrayList<CashConfig> cashConfigs) {
        CashFactory cashFactory = null;
        for (CashConfig configItem : cashConfigs) {
            cashFactory = configItem.getType().getFactory();
            Cash cash = cashFactory.makeCash(configItem.getId(), configItem.getName(), configItem.getValue());
            cofferDenominations.add(new CofferDenomination(cash, configItem.getQuantity()));
        }
    }

    public ArrayList<CofferDenomination> getCofferDenominations() {
        return cofferDenominations;
    }

    // no error checks yet
    public int refillCash() {
        for (CofferDenomination denomination : cofferDenominations) {
            denomination.setQuantity(MAX_QUANTITY);
        }
        return 0;
    }

    public CofferDenomination getDenominationByCashId(int cashId) {
        for (CofferDenomination denomination : cofferDenominations) {
            if (denomination.getCash().getId() == cashId) {
                return denomination;
            }
        }
        return null;
    }

    public boolean payOut(double moneyToBePaid){
//        System.out.println("The amount of money that the vendingg machine has at the beginnign is  :");
//        System.out.println(this.getCofferDenominations().get(0).getQuantity());
//        System.out.println(this.getCofferDenominations().get(1).getQuantity());
//        System.out.println(this.getCofferDenominations().get(2).getQuantity());
//        System.out.println(this.getCofferDenominations().get(3).getQuantity());
//        System.out.println(this.getCofferDenominations().get(4).getQuantity());
//        System.out.println(this.getCofferDenominations().get(5).getQuantity());
//        System.out.println(this.getCofferDenominations().get(6).getQuantity());
        double cumulative = moneyToBePaid;
        ArrayList<CofferDenomination> amountGiven = new ArrayList<CofferDenomination>();
        for(int i = 0; i < cofferDenominations.size();i++){
            amountGiven.add(this.cofferDenominations.get(i).clone(0));
        }
        // amountGiven is set up with 0 as all of itsvalues. Increase this later on.
        for(int i = this.cofferDenominations.size()-1; i>=0; i--){
//            if(cumulative>=cofferDenominations.get(i).cash.getValue()){
                int amount = (int) ((10*cumulative)/(10*amountGiven.get(i).cash.getValue()));
//                System.out.println("cumulative, and the value of this coinage is  "+cumulative+" "+amountGiven.get(i).cash.getValue());
//                System.out.println("amount is "+amount);
                if(amount<=cofferDenominations.get(i).quantity){
//                    System.out.println("OK, an adequate amoutn has been added");
                    amountGiven.get(i).addQuantity(amount);
                    cumulative-=amount*amountGiven.get(i).cash.getValue();
                }else{
                    amountGiven.get(i).addQuantity(cofferDenominations.get(i).quantity);
                    cumulative-=cofferDenominations.get(i).quantity*amountGiven.get(i).cash.getValue();
                }
//            }
        }
        // now I will have done the best I could for takiing out the money that is to be paid.
        if(cumulative!=0){
            System.out.println(cumulative+"is left unpaid");
            System.out.println("Cannot pay you back.");
            return false;
        }else{
            for(int i = this.cofferDenominations.size()-1; i>=0; i--){
                cofferDenominations.get(i).reduceQuantity(amountGiven.get(i).quantity);
            }
//            System.out.println("The money has been taken out.");
//            System.out.println("The amount of money that the vendingg machine has left are :");
//            System.out.println(this.getCofferDenominations().get(0).getQuantity());
//            System.out.println(this.getCofferDenominations().get(1).getQuantity());
//            System.out.println(this.getCofferDenominations().get(2).getQuantity());
//            System.out.println(this.getCofferDenominations().get(3).getQuantity());
//            System.out.println(this.getCofferDenominations().get(4).getQuantity());
//            System.out.println(this.getCofferDenominations().get(5).getQuantity());
//            System.out.println(this.getCofferDenominations().get(6).getQuantity());
            return true;
        }

    }
}
