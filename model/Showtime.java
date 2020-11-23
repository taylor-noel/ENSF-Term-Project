package model;

import java.util.ArrayList;

public class Showtime {
    Movie movie;
    private String start_time;
    private String end_time;
    private ArrayList <Seat> seats;

    public Showtime(String s, String e, ArrayList<Seat> seat, Movie m){
        start_time = s;
        end_time = e;
        seats = seat;
        movie = m;
    }

    public String getStartTime(){
        return start_time;
    }

    public String getEndTime(){
        return end_time;
    }

    public ArrayList <Seat> getSeats(){
        return seats;
    }

    public Movie getMovie(){
        return movie;
    }

    public String toString(){
        return start_time+" to "+end_time;
    }

    
}
