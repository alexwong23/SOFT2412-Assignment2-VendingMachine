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

        double cumulative = (int)(moneyToBePaid*100 - (moneyToBePaid*100)%10); // in cents
        ArrayList<CofferDenomination> amountGiven = new ArrayList<CofferDenomination>();
        for(int i = 0; i < cofferDenominations.size();i++){
            amountGiven.add(this.cofferDenominations.get(i).clone(0));
        }
        // amountGiven is set up with 0 as all of itsvalues. Increase this later on.
        for(int i = this.cofferDenominations.size()-1; i>=0; i--){
                int amount = (int) (cumulative/Math.round(amountGiven.get(i).cash.getValue()*100));
                if(amount<=cofferDenominations.get(i).quantity){
                    amountGiven.get(i).addQuantity(amount);
                    cumulative-=amount*100*amountGiven.get(i).cash.getValue();
                }else{
                    amountGiven.get(i).addQuantity(cofferDenominations.get(i).quantity);
                    cumulative-=cofferDenominations.get(i).quantity*100*amountGiven.get(i).cash.getValue();
                }
        }
        if(cumulative!=0){
            System.out.println( (cumulative) +"is left unpaid");
            System.out.println("Cannot pay you back.");
            return false;
        }else{
            for(int i = this.cofferDenominations.size()-1; i>=0; i--){
                if(amountGiven.get(i).quantity!=0){
                    System.out.println(amountGiven.get(i).quantity+" "+  cofferDenominations.get(i).getCash().getValue()+" dollar note/coin was sent out");
                }
                cofferDenominations.get(i).reduceQuantity(amountGiven.get(i).quantity);
            }

            return true;
        }

    }
}
