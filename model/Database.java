package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import control.*;
import java.io.IOException;

public class Database{
    private ArrayList<Movie> movies;
    private ArrayList<Receipt> tickets;
    private ArrayList<RegisteredUser> registered_users;
    private int currentTicketNum;

    private String path;

    private BufferedReader in;

    Database(){}

    Database(String filepath) throws IOException
    {
        path = filepath;
        // declare a new reader to read from databade file
        in = new BufferedReader(new FileReader(path));
        // initialize blank lists
        movies = new ArrayList<Movie>();
        tickets = new ArrayList<Receipt>();
        registered_users = new ArrayList<RegisteredUser>();

        // skip over [Movies] header
        in.readLine();
        // read in movies
        String line = readItem();
        // keep reading in new movies until there  re none left
        while(line.equals("{"))
        {
            // contruct a new movie with name variable
            Movie newM = new Movie(readItem());
            // read the open brace to begin reading theatres
            line = readItem();
            // keep reading theatres until there are none
            while(line.equals("{"))
            {
                // construct new theatre with specified variables
                Theatre newTh = new Theatre(readItem(), readItem());
                // read open brace to begin reading showtimes
                line = readItem();
                // keep reading shwotimes until there are none
                while(line.equals("{"))
                {
                    // construct new shwotime with read variables
                    Showtime newSh = new Showtime(readItem(), readItem(), newM);
                    // read the open brace to begin reading seats
                    line = readItem();
                    // keep reading seats until there are none
                    while(line.equals("{"))
                    {
                        // construct a new seat 
                        Seat newSeat = new Seat(readItem(0), readItem().charAt(0), readItem(0.0), readItem(true));
                        // add seat to showtime
                        newSh.addSeat((newSeat));
                        // skip closing brace
                        in.readLine();
                        // read next line to either continue reading seats, or go back to reading showtimes
                        line = readItem();
                    }
                    // add showtime to theatre
                    newTh.addShowtime(newSh);
                    // either continue reading more showtimes, or go back to reading theatres
                    line = readItem();
                }
                // add theatre to movie
                newM.addTheatre(newTh);
                // either continue reading theatres, or go back to reading  movies
                line = readItem();
            }
            // add movie to database
            movies.add(newM);
            // either continue reading movies, or continue reading other database variables
            line = readItem();
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

    public void save(){
        try{
            FileWriter out = new FileWriter(path);
            // write Movies header
            out.write("Movies:\n");
            // write all movies
            for(Movie m : movies){
                out.write("{\n");
                out.write("    " + m.getName() + "\n");
                // write all theatres in movie
                for(Theatre t : m.getTheatres()){
                    out.write("    {\n");
                    out.write("        " + t.getName() + "\n");
                    out.write("        " + t.getAddress() + "\n");
                    // write all showtimes in theatre
                    for(Showtime sh : t.getShowtimes()){
                        out.write("        {\n");
                        out.write("            " + sh.getStartTime() + "\n");
                        out.write("            " + sh.getEndTime() + "\n");
                        // write all seats in showtime
                        for(Seat s : sh.getSeats()){
                            out.write("            {\n");
                            out.write("                " + s.getRow() + "\n");
                            out.write("                " + s.getLetter() + "\n");
                            out.write("                " + s.getPrice() + "\n");
                            if(s.isAvailable())
                                out.write("                1\n");
                            else
                                out.write("                0\n");
                            out.write("            }\n");
                        }
                        out.write("        }\n");
                    }
                    out.write("    }\n");
                }
                out.write("}\n");
            }

            // write tickets header
            out.write("\nTickets:\n");
            // write all tickets
            for(Receipt r : tickets){
                out.write("{\n");
                writeUser(out, r.getBuyer(), "    ");
                out.write("    " + r.getNum() + "\n");
                out.write("    " + r.getMovieTitle() + "\n");
                out.write("    " + r.getTheatreName() + "\n");
                out.write("    " + r.getShowtime() + "\n");
                out.write("    " + r.getSeat() + "\n");
                out.write("    " + r.getPrice() + "\n");
                if(r.isCredit())
                    out.write("    1\n");
                else
                    out.write("    0\n");
                out.write("}\n");
            }

            // write registered users header
            out.write("\nRegistered Users:\n");
            // write all registered users
            for(RegisteredUser ru : registered_users)
                writeUser(out, ru, "");

            // write current ticket number header and value
            out.write("\nCurrent Ticket Number:\n");
            out.write("" + currentTicketNum);

            out.close();
        }catch(IOException e) { System.out.println("Error saving database to file\n" + e.getMessage()); }
    }

    private void writeUser(FileWriter out, User u, String lead) throws IOException{
        out.write(lead + "{\n");
        out.write(lead + "    " + u.getFirstName() + "\n");
        out.write(lead + "    " + u.getLastName() + "\n");
        out.write(lead + "    " + u.getAddress() + "\n");
        out.write(lead + "    " + u.getEmail() + "\n");
        out.write(lead + "}\n");
    }

    public void print(){
        System.out.println("\nMovies:");
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

        System.out.println("\nTickets:");
        for(Receipt r : tickets){
            System.out.println(r);
        }
        System.out.println("\nRegistered Users:");
        for(RegisteredUser ru : registered_users){
            System.out.println(ru);
        }
        System.out.println("\nCurrent Ticket Number:\n" + currentTicketNum);
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

    public Movie getMovieAtIndex(int n){
        if(n >= movies.size())
            return null;
        return movies.get(n);
    }

    public Receipt findTicket(int num){
        for(Receipt r : tickets){
            if(r.getNum() == num)
                return r;
        }
        return null;
    }

    public boolean isRegistered(RegisteredUser userToCheck){
        for(RegisteredUser ru : registered_users)
            if(ru.equals(userToCheck))
                return true;
        
        return false;
    }

    public ArrayList<String> getMovieNames(){
        ArrayList<String> names = new ArrayList<String>();
        for(Movie m : movies)
            names.add(m.getName());
        return names;
    }

    public static void main(String[] args) {
        Database db = new Database();
        try{ db = new Database("model/data.txt"); }
        catch(IOException e){ System.out.println("Error reading from database file\n" + e.getMessage()); }

        // db.print();
        db.save();
    }
}