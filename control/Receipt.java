package control;

public class Receipt{
    private User buyer;
    private int ticketNumber;
    private String movie;
    private String theatre;
    private String showtime;
    private String seat;
    private double price;
    private boolean isCredit;

    public Receipt(User b, int num, String m, String t, String show, String s, double p, boolean credit){
        buyer = b;
        ticketNumber = num;
        movie = m;
        theatre = t;
        showtime = show;
        seat = s;
        price = p;
        isCredit = credit;
    }
}