package model;
import java.util.ArrayList;

public class Movie {
    String name;
    ArrayList<Theatre> theatres;

    Movie(String s){
        name = s;
        theatres = new ArrayList<Theatre>();
    }

    public void addTheatre(Theatre t){
        theatres.add(t);
    }

    public String getName() {
        return name;
    }
    public ArrayList<Theatre> getTheatres() {
        return theatres;
    }

    public Theatre searchTheatre(String name){
        for( Theatre t : theatres){
            if(t.getName().toLowerCase().equals(name.toLowerCase()))
                return t;
        }
        return null;
    }

    public String toString(){
        return "Movie name : " + name;
    }
}
