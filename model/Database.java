package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
// import control.*;
import java.io.IOException;

public class Database{
    // private ArrayList<RegisteredUser> registered_users;
    //private ArrayList<Receipt> tickets;
    private ArrayList<Movie> movies;

    private BufferedReader in;

    Database(){
    }

    Database(String filepath) throws IOException
    {
        in = new BufferedReader(new FileReader(filepath));
        movies = new ArrayList<Movie>();

        String line = readItem();
        while(line.equals("{"))
        {
            Movie newM = new Movie(readItem());

            line = readItem();
            while(line.equals("{"))
            {
                Theatre newTh = new Theatre(readItem(), readItem());

                line = readItem();
                while(line.equals("{"))
                {
                    Showtime newSh = new Showtime(in.readLine(), in.readLine(), newM);

                    line = readItem();
                    while(line.equals("{"))
                    {
                        Seat newSeat = new Seat(readItem(0), readItem().charAt(0), readItem(0.0), readItem(true));
                        newSh.addSeat((newSeat));
                        in.readLine();
                        line = readItem();
                    }

                    newTh.addShowtime(newSh);
                    line = readItem();
                }

                newM.addTheatre(newTh);
                line = readItem();
            }

            movies.add(newM);
            line = readItem();
            if(line == null)
                break;
        }

        in.close();
    }

    private String readItem() throws IOException{
        String s = in.readLine();
        if(s == null)
            return null;
        return s.trim();
    }

    private int readItem(int i) throws IOException{
        return Integer.parseInt(in.readLine().replaceAll("\\s+", ""));
    }

    private double readItem(double i) throws IOException{
        return Double.parseDouble(in.readLine().replaceAll("\\s+", ""));
    }

    private boolean readItem(boolean b) throws IOException{
        return Integer.parseInt(in.readLine().replaceAll("\\s+", "")) > 0;
    }

    public void print(){
        for(Movie m : movies)
        {
            System.out.println(m);
            for(Theatre t : m.getTheatres())
            {
                System.out.println("  " + t);
                for(Showtime sh : t.getShowtimes())
                {
                    System.out.println("    " + sh);
                    for(Seat s : sh.getSeats())
                        System.out.println("      " + s);
                }
            }
        }
    }

    // public ArrayList<Receipt> getTicket(){
    //     return tickets;
    // }

    // public ArrayList<RegisteredUser> getUsers(){
    //     return registered_users;
    // }

    public static void main(String[] args) {
        Database db = new Database();
        try{ db = new Database("model/data.txt"); }
        catch(IOException e){ System.out.println("Error reading from database file\n" + e.getMessage()); }
        System.out.println("Success");

        db.print();
    }
}