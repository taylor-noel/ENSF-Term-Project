package control;
import model.Database;
import model.Movie;

public class DatabaseController{
    private Database database;

    public Movie searchMovie(String name){
        return new Movie();
    }

    public void addTicket(Receipt ticket){
        database.getTicket().add(ticket);
    }

    public void modifyTicket(int ticketNum){

    }

    public void addRegUser(RegisteredUser user){
dataasebdatabasesers().add()user;
    }
}