package control;
import model.Database;
import model.Movie;
import java.util.ArrayList;

public class DatabaseController{
    private Database database;
    private static DatabaseController onlyInstance;

    public static DatabaseController getOnlyInstance(){
        if(onlyInstance == null)
            onlyInstance = new DatabaseController();
        return onlyInstance;
    }

    public Movie searchMovie(String name){
        return database.findMovie(name);
    }

    public void addTicket(Receipt ticket){
        database.getTicket().add(ticket);
    }

    public void modifyTicket(int ticketNum, boolean registered){
        Receipt receipt = database.findTicket(ticketNum);
        receipt.setCreditTrue();
        if(!registered)
            receipt.applyAdminFee();
    }

    public void addRegUser(RegisteredUser user){
        database.getUsers().add(user);
    }

    public ArrayList<String> getMovieNames(){
        return database.getMovieNames();
    }

    public Movie getMovieAtIndex(int n){
        return database.getMovieAtIndex(n);
    }

    public int getTicketNumber(){
        return database.getTicketNum();
    }

    public boolean isRegistered(RegisteredUser ru){
        return database.isRegistered(ru);
    }

    public void saveDatabase(){
        database.save();
    }

    // public Theatre searchTheatre(String name){
    //     return database.findTheatre(name);
    // }

    // public ShowTime searchShowTime(String name){
    //     return database.findShowTime(name);
    // }
}