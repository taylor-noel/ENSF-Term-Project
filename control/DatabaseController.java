package control;

import model.Database;
import model.Movie;
import model.Showtime;

import java.io.IOException;
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

    public static DatabaseController getOnlyInstance() {
        if (onlyInstance == null)
            onlyInstance = new DatabaseController();
        return onlyInstance;
    }

    public Movie searchMovie(String name) {
        return database.findMovie(name);
    }

    public void addTicket(Receipt ticket) {
        database.getTicket().add(ticket);
        saveDatabase();
    }

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

    public void addRegUser(RegisteredUser user) {
        database.getUsers().add(user);
        saveDatabase();
    }

    public ArrayList<String> getMovieNames() {
        return database.getMovieNames();
    }

    public Movie getMovieAtIndex(int n) {
        return database.getMovieAtIndex(n);
    }

    public int getTicketNumber() {
        return database.getTicketNum();
    }

    public boolean isRegistered(RegisteredUser ru) {
        return database.isRegistered(ru);
    }

    public void saveDatabase() {
        database.save();
    }

    // public Theatre searchTheatre(String name){
    // return database.findTheatre(name);
    // }

    // public ShowTime searchShowTime(String name){
    // return database.findShowTime(name);
    // }
}