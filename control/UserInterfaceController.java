package control;
import model.*;
import view.*;
import java.util.ArrayList;


public class UserInterfaceController{
    //private UserInterface UI;
    private MovieSearcher movie_searcher;
    private SeatSelector seat_selector;
    private PurchaseProcess purchase_process;
    private RegisterProcess register_process;
    private RefundProcess refund_process;
    private Movie selected_movie;
    private ArrayList <String> ticketInfo;
    private ArrayList <String> userInfo;

    // public void getMenuOption(){

    // }

    // public void updateMenu(){

    // }

    public void setMovie(String movie){
        selected_movie = movie_searcher.searchMovie(movie);
    }

    public void setSeat(String seat){
        seat_selector.selectSeat(seat);

    }

    public void startPurchase(){
        purchase_process.createReceipt();
    }

    public void startRefund(){
        refund_process.modifyReciept();
    }

    public ArrayList <String> getUserInfo(){
        return userInfo;
    }

    public void registerUser(String userInfo) {
        // register_process = new RegisterProcess(/* userInfo */);
    }
}