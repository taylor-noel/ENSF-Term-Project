package control;
import model.*;
import view.*;
import java.util.ArrayList;


public class UserInterfaceController{
    private UserInterface UI;
    private MovieSearcher movie_searcher;
    private SeatSelector seat_selector;
    private PurchaseProcess purchase_process;
    private RegisterProcess register_process;
    private RefundProcess refund_process;
    
    private ArrayList <String> ticketInfo;
    private ArrayList <String> userInfo;

    private Movie selected_movie;

    public UserInterfaceController(){
        movie_searcher = new MovieSearcher();
        UI = new UserInterface(this);
    }

    public boolean setMovie(String movie){
        selected_movie = movie_searcher.searchMovie(movie);
        seat_selector = new SeatSelector(selected_movie);
        if(selected_movie == null){
            return false;
        }
        return true;
    }

    public ArrayList<Theatre> getTheatres(){
        return selected_movie.getTheatres();
    }

    public void setTheatre(String theatreName){
        seat_selector.selectTheatre(theatreName);
    }

    public ArrayList<Showtime> getShowtimes(){
        return seat_selector.getSelectedTheatre().getShowtimes();
    }

    public void setShowTime(String showTime){
        seat_selector.selectShowtime(showTime);;
    }

    public boolean setSeat(ArrayList<String> seat){
        seat_selector.selectSeat(seat);
        if(seat_selector.getSelectedSeat() == null){
            return false;
        }
        return true;
    }

    public void startPurchase(){
        purchase_process.createReceipt();
    }

    public void startRefund(int ticketNum){
        refund_process.modifyReciept(ticketNum);
    }

    public void getUserInfo(){
       // this.userInfo = UI.getUserInfo();
    }

    public void registerUser() {
        // userInfo.add("t");
        // register_process = new RegisterProcess(/* userInfo */);
    }
}