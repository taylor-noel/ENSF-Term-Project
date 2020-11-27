package control;
import model.*;

public class DatabaseController{
    private Database database;

    public Movie searchMovie(String name){
        return database.findMovie(name);
    }

    public void addTicket(Receipt ticket){
        database.getTicket().add(ticket);
    }

//get the ticket from database finder and then change the receipt so that isCredit is true
    public void modifyTicket(int ticketNum){
        
    }

    public void addRegUser(RegisteredUser user){
        database.getUsers().add(user);
    }
}