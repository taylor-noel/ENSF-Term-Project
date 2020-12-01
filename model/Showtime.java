package model;

import java.util.ArrayList;

public class Showtime {
    Movie movie;
    private String start_time;
    private String end_time;
    private ArrayList<Seat> seats;

    public Showtime(String s, String e, ArrayList<Seat> seat, Movie m) {
        start_time = s;
        end_time = e;
        seats = seat;
        movie = m;
    }

    public Showtime(String s, String e, Movie m) {
        start_time = s;
        end_time = e;
        movie = m;
        seats = new ArrayList<Seat>();
    }

    public void addSeat(Seat s) {
        seats.add(s);
    }

    public String getStartTime() {
        return start_time;
    }

    public String getEndTime() {
        return end_time;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public Seat selectSeat(ArrayList<String> seat) {
        int row = Integer.parseInt(seat.get(0));
        char letter = seat.get(1).charAt(0);
        for (Seat s : seats) {
            if ((s.getRow() == row) && (s.getLetter() == letter))
                return s;
        }
        return null;
    }

    public String toString() {
        return "Showtime : " + start_time + " to " + end_time + ",\t Movie : " + movie.toString();
    }
}
