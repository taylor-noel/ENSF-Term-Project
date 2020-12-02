package control;

import java.util.ArrayList;
import model.*;

public class SeatSelector {
    private Movie movie;
    private Theatre theatre;
    private Showtime showtime;
    private Seat seat;

    public SeatSelector(Movie selectedMov) {
        movie = selectedMov;
    }

    //searches the list of theatres for the selected movie and 
    // returns true if there is a theatre with the name name
    public boolean selectTheatre(String name) {
        theatre = movie.searchTheatre(name);
        return theatre != null;
    }

    //searches the list of showtimes for the selected theatre and 
    // returns true if there is a showtime with at index index
    public boolean selectShowtime(int index) {
        showtime = theatre.searchShowtime(index);
        return showtime != null;
    }

    //selects the seat matching information s
    public void selectSeat(ArrayList<String> s) {
        seat = showtime.selectSeat(s);
    }

    //selects the seat chosen by user
    public void selectSeat(String id) {
        for (Seat s : showtime.getSeats()) {
            if (s.getLetter() == id.charAt(1) && s.getRow() == Integer.parseInt(id.charAt(0) + "")) {
                seat = s;
                return;
            }
        }
    }

    //returns the selected theatre
    public Theatre getSelectedTheatre() {
        return theatre;
    }

    //returns the selected showtime
    public Showtime getSelectedShowTime() {
        return showtime;
    }

    //returns the selected seat
    public Seat getSelectedSeat() {
        return seat;
    }

    //returns the movie, theatre, showtime, and seat infomration as an arrayList
    public ArrayList<String> getInfo() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(movie.getName());
        list.add(theatre.getName());
        list.add(showtime.getStartTime() + " to " + showtime.getEndTime());
        list.add(showtime.getDate());
        list.add(seat.getRow() + "" + seat.getLetter() + "");
        list.add(seat.getPrice() + "");
        seat.setAvailable(false);

        return list;
    }
}