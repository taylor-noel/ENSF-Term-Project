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
    }

    public ArrayList<String> getMovies() {

        // return (databaseController.getMovieNamess());
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

    public void setTheatre(String theatreName) {
        seat_selector.selectTheatre(theatreName);
    }

    public ArrayList<String> getShowtimes() {
        ArrayList<String> sTime = new ArrayList<>();
        for (Showtime s: seat_selector.getSelectedTheatre().getShowtimes(){
            sTime.add(s.toString());
        }
        return sTime;
    }

    public ArrayList<String> getAllSeats() {
        for (Seat s : seat_selector.getSelectedShowTime().getSeats()) {
            // send all seats
        }
    }

    public void setShowTime(String showTime) {
        seat_selector.selectShowtime(showTime);
        ;
    }

    public boolean setSeat(ArrayList<String> seat) {
        seat_selector.selectSeat(seat);
        if (seat_selector.getSelectedSeat() == null) {
            return false;
        }
        return true;
    }

    public void startPurchase() {
        purchase_process.createReceipt();
    }

    public void startRefund(int ticketNum) {
        refund_process.modifyReciept(ticketNum);
    }

    public void getUserInfo() {
        // this.userInfo = UI.getUserInfo();
    }

    public void registerUser() {
        // userInfo.add("t");
        // register_process = new RegisterProcess(/* userInfo */);
    }
}