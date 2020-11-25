package control;

import model.*;
import view.*;
import java.util.ArrayList;

public class UserInterfaceController {
    private DatabaseController dbController;
    private UserInterface UI;
    private MovieSearcher movie_searcher;
    private SeatSelector seat_selector;
    private PurchaseProcess purchase_process;
    private RegisterProcess register_process;
    private RefundProcess refund_process;
    private Movie selected_movie;
    private ArrayList<String> ticketInfo;
    private ArrayList<String> userInfo;

    public UserInterfaceController() {
        dbController = new DatabaseController();
        UI = new UserInterface(this);
    }

    public void getMenuOption() {
        System.out.println("1. Search Movie");
        System.out.println("2. Exit");

        // if input is choice "1"
        // update menu
    }

    public void updateMenu() {
        // show updated menu and then do actions

        // selected_movie = dbController.searchMovie(name_of_movie);

    }

    public Movie getMovie() {
        return selected_movie;
    }

    public void getSeat() {
        // System.out.println("Seat");
    }

    public void startPurchase() {
        // System.out.println("purchase");
    }

    public void startRefund() {
        // System.out.println("refund");
    }

    public void getUserInfo(String first, String last, String add, String email) {
        // adds the user to database (registered)
        dbController.addRegUser(new RegisteredUser(first, last, add, email, true));
    }

    public static void main(String[] args) {
        new UserInterfaceController();
    }
}