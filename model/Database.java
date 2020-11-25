package model;

import java.util.ArrayList;
import control.*;

public class Database {
    private ArrayList<RegisteredUser> registered_users;
    private ArrayList<Receipt> tickets;
    private ArrayList<Movie> movies;

    public Database(String filepath) {

    }

    public void print() {
        for (Movie m : movies) {
            for (Theatre t : m.getTheatres()) {
                for (Showtime sh : t.getShowtimes()) {
                    for (Seat s : sh.getSeats())
                        System.out.println(s);

                    System.out.println(sh);
                }
                System.out.println(t);
            }
            System.out.println(m);
        }
    }

    public ArrayList<Receipt> getTicket() {
        return tickets;
    }

    public ArrayList<RegisteredUser> getUsers() {
        return registered_users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}