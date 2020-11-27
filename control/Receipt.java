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
    
    public void setCredit(boolean credit){
        isCredit = credit;
    }

    public int getNum(){
        return ticketNumber;
    }

    public String toString(){
        return "User : " + buyer + "\nNumber : " + ticketNumber + " Movie : " + movie + " Theatre : " + theatre
         + " Showtime : " + showtime + " Seat : " + seat + " Price : " + price + " Credit" + isCredit;
    }
}