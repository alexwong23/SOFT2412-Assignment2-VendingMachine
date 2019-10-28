package VendingMachine.model;

public class NoteFactory implements CashFactory {

    @Override
    public Cash makeCash(int id, String name, double value) { return new Note(id, name, value); }
}
