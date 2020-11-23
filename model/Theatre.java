package model;

import java.util.ArrayList;

public class Theatre{
    private String name;
    private String address;
    private ArrayList<Showtime> showtimes;

    Theatre(String n, String a, ArrayList<Showtime> s){
        name = n;
        address = a;
        showtimes = s;
    }
    
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    
    public ArrayList<Showtime> getShowtimes(){
        return showtimes;
    }
}
