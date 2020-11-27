package control;

import model.*;

public class SeatSelector{
    private Theatre theatre;
    private Showtime showtime;
    private Seat seat;

    public String selectTheatre(){

    }

    public String selectShowtime(){

    }

    public void selectSeat(String seat){
        seat = new Seat(seat);
    }
}