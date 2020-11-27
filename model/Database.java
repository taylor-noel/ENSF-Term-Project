package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import control.*;
import java.io.IOException;

public class Database{
    private ArrayList<RegisteredUser> registered_users;
    private ArrayList<Receipt> tickets;
    private ArrayList<Movie> movies;
    private int currentTicketNum;

    private BufferedReader in;

    Database(){
    }

    Database(String filepath) throws IOException
    {
        in = new BufferedReader(new FileReader(filepath));
        movies = new ArrayList<Movie>();
        tickets = new ArrayList<Receipt>();
        registered_users = new ArrayList<RegisteredUser>();

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
            if(!line.equals("}")){
                System.out.println("I should not be seeing this");
                break;
            }
        }

        while(!line.equals("Tickets:"))
            line = readItem();

        line = readItem();
        while(line.equals("{")){
            readItem();
            User newU = new User(readItem(), readItem(), readItem(), readItem());
            readItem();
            Receipt newR = new Receipt(newU, readItem(0), readItem(), readItem(),
                readItem(), readItem(), readItem(0.0), readItem(true));
            tickets.add(newR);
            readItem();
            line = readItem();
        }

        while(!line.equals("Registered Users:"))
            line = readItem();

        line = readItem();
        while(line.equals("{")){
            RegisteredUser newRU = new RegisteredUser(readItem(), readItem(), readItem(), readItem(), true);
            registered_users.add(newRU);
            readItem();
            line = readItem();
        }

        while(!line.equals("Current Ticket Number:"))
            line = readItem();
        currentTicketNum = readItem(0);

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

    public ArrayList<Receipt> getTicket(){
        return tickets;
    }

    public ArrayList<RegisteredUser> getUsers(){
        return registered_users;
    }

    public int getTicketNum(){
        return currentTicketNum++;
    }

    public Movie findMovie(String s){
        for( Movie m : movies){
            if(m.getName().equals(s))
                return m;
        }
        return null;
    }

    public Receipt findTicket(int num){
        for(Receipt r : tickets){
            if(r.getNum() == num)
                return r;
        }
        return null;
    }

    public static void main(String[] args) {
        Database db = new Database();
        try{ db = new Database("model/data.txt"); }
        catch(IOException e){ System.out.println("Error reading from database file\n" + e.getMessage()); }
        System.out.println("Success");

        db.print();
    }
}