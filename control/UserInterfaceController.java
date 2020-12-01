package control;

import model.*;
import view.*;
import java.util.ArrayList;

public class UserInterfaceController {
    private UserInterface UI;
    private MovieSearcher movie_searcher;
    private SeatSelector seat_selector;
    private PurchaseProcess purchase_process;
    private RegisterProcess register_process;
    private RefundProcess refund_process;

    private ArrayList<String> ticketInfo;
    private ArrayList<String> userInfo;

    private Movie selected_movie;

    public UserInterfaceController() {
        movie_searcher = new MovieSearcher();
        UI = new UserInterface(this);
        userInfo = new ArrayList<String>();
        ticketInfo = new ArrayList<String>();
        userInfo = UI.getUserInfo();

        refund_process = new RefundProcess();
    }

    public ArrayList<String> getMovies() {
        return (DatabaseController.getOnlyInstance().getMovieNames());
    }

    public boolean setMovie(String movie) {
        selected_movie = movie_searcher.searchMovie(movie);
        if (selected_movie == null) {
            return false;
        }
        seat_selector = new SeatSelector(selected_movie);
        return true;
    }

    public ArrayList<String> getTheatres() {
        ArrayList<String> tNames = new ArrayList<>();
        for (Theatre t : selected_movie.getTheatres()) {
            tNames.add(t.getName());
        }
        return tNames;
    }

    public boolean setTheatre(String theatreName) {
        return seat_selector.selectTheatre(theatreName);
    }

    public ArrayList<String> getShowtimes() {
        ArrayList<String> sTime = new ArrayList<>();
        for (Showtime s : seat_selector.getSelectedTheatre().getShowtimes()) {
            sTime.add(s.toString());
            System.out.println(s.toString());
        }
        return sTime;
    }

    public ArrayList<String> getAllSeats() {
        ArrayList<String> seatInfo = new ArrayList<>();
        for (Seat s : seat_selector.getSelectedShowTime().getSeats()) {
            System.out.println(s.toString());
            seatInfo.add(s.getRow() + " " + s.getLetter() + " " + s.getPrice() + " " + s.isAvailable());
        }
        return seatInfo;
    }

    public boolean setShowTime(int index) {
        return seat_selector.selectShowtime(index);
    }

    public boolean setSeat(ArrayList<String> seat) {
        seat_selector.selectSeat(seat);
        if (seat_selector.getSelectedSeat() == null) {
            return false;
        }
        return true;
    }

    public void setSeat(String id) {
        seat_selector.selectSeat(id);
    }

    public String purchaseTicket() {

        // checks to see if the user has inputted valid information and selected a seat
        if (!userInfo.isEmpty()) {
            purchase_process = new PurchaseProcess(seat_selector.getInfo(), userInfo);

            purchase_process.createReceipt();
            purchase_process.addReciept();

            // send a email of the reciept to user
            purchase_process.emailUser();

            // print reciept to the text area
            return "A copy of the reciept has been emailed to " + userInfo.get(3) + "\n\n"
                    + purchase_process.getReceipt().toString();
        } else {
            // pop up informing user to select a seat first
            return "Cannot purchase a ticket. Restart and enter valid user information";
        }
    }

    // creates and uses the RefundProcess class to turn a previous receipt into a
    // ticket
    // if the user is registered, they will not be charged a 15% admin fee
    public boolean refundTicket(int receipt_number) {
        // create a Refund Process object
        if (refund_process.modifyReciept(receipt_number, Boolean.parseBoolean(userInfo.get(4)))) {
            return true;
        }
        return false;
    }

    public void getUserInfo() {
        System.out.println("\n\n" + UI + "\n");
        userInfo = new ArrayList<String>();
        userInfo = UI.getUserInfo();
    }

    // checks database to see if user is registered
    public boolean checkRegUser(RegisteredUser ru) {
        return DatabaseController.getOnlyInstance().isRegistered(ru);
    }

    // adds the user as a registered user
    public void registerUser() {
        register_process = new RegisterProcess(userInfo);
        register_process.addRegisteredUser();
        userInfo.set(4, "true");
    }

    // return the selected theatre is seat_selector
    public boolean getTheatre() {
        return seat_selector.getSelectedTheatre() != null;
    }

    public boolean getShowtime() {
        return seat_selector.getSelectedShowTime() != null;
    }

    public static void main(String[] args) {
        UserInterfaceController uic = new UserInterfaceController();
    }
}