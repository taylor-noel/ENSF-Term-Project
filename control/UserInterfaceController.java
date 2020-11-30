package control;

import model.*;
import view.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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

    // public ArrayList<String> getMovies() {

    //     return (databaseController.getMovieNames());
    // }

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
        for (Showtime s: seat_selector.getSelectedTheatre().getShowtimes()){
            sTime.add(s.toString());
        }
        return sTime;
    }

    public ArrayList<String> getAllSeats() {
        ArrayList<String> seatInfo = new ArrayList<>();
        for (Seat s : seat_selector.getSelectedShowTime().getSeats()) {
           seatInfo.add(s.getRow()+" "+s.getLetter()+" "+s.getPrice()+" "+s.isAvailable());
        }
        return seatInfo;
    }

    public void setShowTime(String showTime) {
        seat_selector.selectShowtime(showTime);
    }

    public boolean setSeat(ArrayList<String> seat) {
        seat_selector.selectSeat(seat);
        if (seat_selector.getSelectedSeat() == null) {
            return false;
        }
        return true;
    }

    public String purchaseTicket() {
        //checks to see if the user has inputted valid information and selected a seat 
        if(!ticketInfo.isEmpty() && !userInfo.isEmpty()){
            purchase_process = new PurchaseProcess(ticketInfo, userInfo);
            purchase_process.createReceipt();
        
            //popup for confirming purchase 
            int confirmed = JOptionPane.showConfirmDialog(null, "Confirm Purchase?", "User Login", JOptionPane.YES_NO_OPTION);

            //if the user cancels
            if(confirmed == JOptionPane.NO_OPTION){
                
                //return cancel message 
                return "Payment has been cancelled";

            }

            //send a email of the reciept to user 
            // purchase_process.emailUser();

            //print reciept to the text area 
            return purchase_process.getReceipt().toString();
        }else{
            //pop up informing user to select a seat first 
            return "Cannot purchase a ticket. Please select a seat first";
        }
    }

    public void refundTicket() {
        //create a Refund Process object 
        refund_process = new RefundProcess(); 

        //ask user to input reciept number
        String receipt_number = JOptionPane.showInputDialog(null, "Please enter the Ticket Number on the ticket you want to Refund\n" +
        "NOTE: You will be charged a 15% admin fee if you are not a registered user", "Refund");

        //check for registered user (15% admin fee)
        if(receipt_number != null){
            refund_process.modifyReciept(receipt_number, Boolean.parseBoolean(userInfo.get(4)));
            JOptionPane.showMessageDialog(null, "Your receipt has been turned into a credit");
        }else{
            JOptionPane.showMessageDialog(null, "You did not enter a valid receipt number. Please try again");
        }
    }

    public void getUserInfo() {
        this.userInfo = UI.getUserInfo();
    }

    //checks database to see if user is registered 
    public boolean checkRegUser(RegisteredUser ru) {
        return DatabaseController.getOnlyInstance().isRegistered(ru);
    }

    //adds the user as a registered user 
    public void registerUser(){

        //asks user to confirm if they want to register 
        JOptionPane.showConfirmDialog(null, "Press Ok to confirm your registrations", "User Registeration", JOptionPane.CANCEL_OPTION);

        //registers user 
        RegisterProcess rp = new RegisterProcess(userInfo);
    }
}