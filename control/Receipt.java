package control;

public class Receipt {
    private User buyer;
    private int ticketNumber;
    private String movie;
    private String theatre;
    private String showtime;
    private String seat;
    private double price;
    private boolean isCredit;

    public Receipt(User b, int num, String m, String t, String show, String s, double p, boolean credit) {
        buyer = b;
        ticketNumber = num;
        movie = m;
        theatre = t;
        showtime = show;
        seat = s;
        price = p;
        isCredit = credit;
    }

    public void setCreditTrue() {
        isCredit = true;
    }

    public User getBuyer() {
        return buyer;
    }

    public int getNum() {
        return ticketNumber;
    }

    public String getMovieTitle() {
        return movie;
    }

    public String getTheatreName() {
        return theatre;
    }

    public String getShowtime() {
        return showtime;
    }

    public String getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public void applyAdminFee() {
        price *= 0.85;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public String toString() {
        return "\nUser : " + buyer + "\n\nNumber : " + ticketNumber + "\nMovie : " + movie + "\nTheatre : " + theatre
                + "\nShowtime : " + showtime + "\nSeat : " + seat + "\nPrice : " + price + "\nCredit : " + isCredit;
    }
}