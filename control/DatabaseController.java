package control;

import model.Database;
import model.Movie;
import java.util.ArrayList;

public class DatabaseController {
    private Database database;
    private static DatabaseController onlyInstance;

    DatabaseController() {
        try {
            database = new Database("model/data.txt");
        } catch (Exception e) {
            System.out.println("Error reading from database" + e.getMessage());
        }
    }

    //makes DatabaseController a singleton
    public static DatabaseController getOnlyInstance() {
        if (onlyInstance == null)
            onlyInstance = new DatabaseController();
        return onlyInstance;
    }

    //searches the database for a movie with the name name
    public Movie searchMovie(String name) {
        return database.findMovie(name);
    }

    //adds ticket to the list of Receipts in the database
    public void addTicket(Receipt ticket) {
        database.getTicket().add(ticket);
        saveDatabase();
    }

    //modifies the ticket with number ticketNum and if the user 
    //is not registered there is an admin fee applied to the refund
    public boolean modifyTicket(int ticketNum, boolean registered) {
        Receipt receipt = database.findTicket(ticketNum);
        if (receipt == null)
            return false;
        receipt.setCreditTrue();
        database.openUpSeat(receipt);
        if (!registered)
            receipt.applyAdminFee();
        saveDatabase();
        return true;
    }

    //returns the Receipt corresponding to ticketNum
    public Receipt getTicket(int ticketNum) {
        return database.findTicket(ticketNum);
    }

    //adds user to the list of registered users in database and then saves the database
    public void addRegUser(RegisteredUser user) {
        database.getUsers().add(user);
        saveDatabase();
    }

    //returns an array list of strings containing all the movie names from the database
    public ArrayList<String> getMovieNames() {
        return database.getMovieNames();
    }

    //returns the movie at index n from the database arraylist of movies
    public Movie getMovieAtIndex(int n) {
        return database.getMovieAtIndex(n);
    }

    //reutrns the ticket number
    public int getTicketNumber() {
        return database.getTicketNum();
    }

    //checks to see if the user is registered
    public boolean isRegistered(RegisteredUser ru) {
        return database.isRegistered(ru);
    }

    //saves the current database
    public void saveDatabase() {
        database.save();
    }

}