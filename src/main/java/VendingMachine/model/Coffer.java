package VendingMachine.model;

import VendingMachine.config.CashConfig;

import java.util.ArrayList;

public class Coffer {

    private ArrayList<CofferDenomination> cofferDenominations = new ArrayList<>();

    private static int MAX_QUANTITY = 10;

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

    public CofferDenomination getDenominationByCashId(int cashId) {
        for (CofferDenomination denomination : cofferDenominations) {
            if (denomination.getCash().getId() == cashId) {
                return denomination;
            }
        }
        return null;
    }

    public boolean payOut(double moneyToBePaid){
        double cumulative = moneyToBePaid;
        ArrayList<CofferDenomination> amountGiven = new ArrayList<CofferDenomination>();
        for(int i = 0; i < cofferDenominations.size();i++){
            amountGiven.add(this.cofferDenominations.get(i).clone(0));
        }
        // amountGiven is set up with 0 as all of itsvalues. Increase this later on.
        for(int i = this.cofferDenominations.size()-1; i>=0; i--){
            if(cumulative>cofferDenominations.get(i).cash.getValue()){
                int amount = (int) cumulative/amountGiven.get(i).quantity;
                if(amount<=cofferDenominations.get(i).quantity){
                    amountGiven.get(i).quantity+=amount;
                    cumulative-=amount*amountGiven.get(i).cash.getValue();
                }else{
                    amountGiven.get(i).quantity+=cofferDenominations.get(i).quantity;
                    cumulative-=cofferDenominations.get(i).quantity*amountGiven.get(i).cash.getValue();
                }
            }
        }
        // now I will have done the best I could for takiing out the money that is to be paid.
        if(cumulative!=0){
            System.out.println("Cannot pay you back.");
            return false;
        }else{
            for(int i = this.cofferDenominations.size()-1; i>=0; i--){
                cofferDenominations.get(i).quantity-=amountGiven.get(i).quantity;
            }
            System.out.println("The money has been taken out.");
            return true;
        }

    }

}
