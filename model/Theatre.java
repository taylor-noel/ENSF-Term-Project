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

    Theatre(String n, String a){
        name = n;
        address = a;
        showtimes = new ArrayList<Showtime>();
    }

    public void addShowtime(Showtime s){
        showtimes.add(s);
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

    public Showtime searchShowtime(String start){
        for( Showtime s : showtimes){
            if(s.getStartTime().equals(name))
                return s;
        }
        return null;
    }

    public String toString(){
        return "Theatre name : " + name + ", Address : " + address;
    }
}
