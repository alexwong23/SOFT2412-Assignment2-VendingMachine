package VendingMachine.model;

public enum CashEnum {
    COIN {
        public CashFactory getFactory() {
            return new CoinFactory();
        }
    },
    NOTE {
        public CashFactory getFactory() {
            return new NoteFactory();
        }
    };

    public abstract CashFactory getFactory();
}
