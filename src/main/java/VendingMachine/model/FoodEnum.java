package VendingMachine.model;

public enum FoodEnum {
    DRINK {
        public FoodFactory getFactory() {
            return new DrinkFactory();
        }
    },
    CHOCOLATE {
        public FoodFactory getFactory() {
            return new ChocolateFactory();
        }
    },
    CHIPS {
        public FoodFactory getFactory() {
            return new ChipsFactory();
        }
    },
    LOLLY {
        public FoodFactory getFactory() {
            return new LollyFactory();
        }
    };

    public abstract FoodFactory getFactory();
}