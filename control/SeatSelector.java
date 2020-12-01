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

    public boolean selectTheatre(String name){
        theatre = movie.searchTheatre(name);
        return theatre != null;
    }   
        
    public boolean selectShowtime(int index){
       showtime = theatre.searchShowtime(index);
       return showtime != null;
    }

    public void selectSeat(ArrayList<String> s){
        seat = showtime.selectSeat(s);
    }

    public void selectSeat(String id){
        for(Seat s : showtime.getSeats()){
            if(s.getLetter() == id.charAt(1) && s.getRow() == Integer.parseInt(id.charAt(0) + "")){
                seat = s;
                return;
            }
        }
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

    public ArrayList<String> getInfo(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(movie.getName());
        list.add(theatre.getName());
        list.add(showtime.getStartTime() + " to " + showtime.getEndTime());
        list.add(seat.getRow() + "" + seat.getLetter() + "");
        list.add(seat.getPrice() + "");
        seat.setAvailable(false);

        return list;
    }
}