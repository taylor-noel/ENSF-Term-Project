package control;
import java.util.ArrayList;
import model.*;

public class SeatSelector{
    private Movie movie;
    private Theatre theatre;
    private Showtime showtime;
    private Seat seat;

    public SeatSelector(Movie selectedMov){
        movie = selectedMov;
    }

    public void selectTheatre(String name){
        theatre = movie.searchTheatre(name);
    }   
        
    public void selectShowtime(String start){
       showtime = theatre.searchShowtime(start);
    }

    public void selectSeat(ArrayList<String> s){
        seat = showtime.selectSeat(s);
    }

    public Theatre getSelectedTheatre(){
        return theatre;
    }

    public Showtime getSelectedShowTime(){
        return showtime;
    }

    public Seat getSelectedSeat(){
        return seat;
    }
}