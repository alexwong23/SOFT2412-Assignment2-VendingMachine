package VendingMachine.model;

public class Money {
    //    Amount value;
//    enum Amount{
//    TENC, TWENTYC, FIFTYC, ONE, TWO, FIVE, TEN, TWENTY;
//    }
    public int tenCents;
    public int twentyCents;
    public int fiftyCents;
    public int one;
    public int two;
    public int five;
    public int ten;
    public int twenty;
    public int fifty;
    public int hundred;

    public double totalMoney(){
        return (double)(tenCents * 0.1 + twentyCents * 0.2 + fiftyCents * 0.5 + one + two * 2 + five * 5 + ten * 10 + twenty * 20 + fifty * 50 + hundred * 100);

    }

}
