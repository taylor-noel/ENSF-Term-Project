package control;

import model.Database;
import model.Movie;

public class DatabaseController {
    private Database database;

    public DatabaseController() {

        // filepath to the database needs to added here
        // this loads the database
        database = new Database(filepath);
    }

    public Movie searchMovie(String name) {
        return (new MovieSearcher(database).searchMovie(name));
    }

    public void addTicket(Receipt ticket) {
        database.getTicket().add(ticket);
    }

    public void modifyTicket(int ticketNum) {
        for (Receipt ticket : database.getTicket()) {
            if (ticket.getTicketNumber() == ticketNum) {
                // modify
            }
        }
    }

    public void addRegUser(RegisteredUser user) {
        // dataasebdatabasesers().add()user;
        database.getUsers().add(user);
    }
}