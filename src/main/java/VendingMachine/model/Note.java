package VendingMachine.model;

public class Note implements Cash {

   private String name;
   private double value;
    private int id;

    public Note(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Override
    public CashEnum getType() { return CashEnum.NOTE; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Cash clone() {
        return new Note(this.id, this.name, this.value);
    }

    @Override
    public int getId() {
        return id;
    }
}
