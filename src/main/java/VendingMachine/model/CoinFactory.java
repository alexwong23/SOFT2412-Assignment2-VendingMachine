package VendingMachine.model;

public class CoinFactory implements CashFactory {

    @Override
    public Cash makeCash(int id, String name, double value) {
        return new Coin(id, name, value);
    }
}
