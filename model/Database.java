package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import control.*;
import java.io.IOException;

public class Database {
    private ArrayList<Movie> movies;
    private ArrayList<Receipt> tickets;
    private ArrayList<RegisteredUser> registered_users;
    private int currentTicketNum;

    private String path;
    private String pathM;

    private BufferedReader in;

    Database() {
    }

    /** parses data fropm file into data mambers */
    public Database(String filepath) throws Exception {
        pathM = "model/iniData.txt";
        path = filepath;
        // declare a new reader to read from databade file
        in = new BufferedReader(new FileReader(pathM));
        // initialize blank lists
        movies = new ArrayList<Movie>();
        tickets = new ArrayList<Receipt>();
        registered_users = new ArrayList<RegisteredUser>();

        System.out.println("Database working 35");

        // skip over [Movies] header
        in.readLine();
        String line = readItem();
        // read in movies

        // keep reading in new movies until there re none left
        while (line.equals("{")) {
            // contruct a new movie with name variable
            Movie newM = new Movie(readItem());
            // read the open brace to begin reading theatres
            line = readItem();
            // keep reading theatres until there are none
            while (line.equals("{")) {
                // construct new theatre with specified variables
                Theatre newTh = new Theatre(readItem(), readItem());
                // read open brace to begin reading showtimes
                line = readItem();
                // keep reading shwotimes until there are none
                while (line.equals("{")) {
                    // construct new shwotime with read variables
                    Showtime newSh = new Showtime(readItem(), readItem(), readItem(), newM);
                    // read the open brace to begin reading seats
                    line = readItem();
                    // keep reading seats until there are none
                    while (line.equals("{")) {
                        // construct a new seat
                        Seat newSeat = new Seat(readItem(0), readItem().charAt(0), readItem(0.0), readItem(true));
                        // add seat to showtime
                        newSh.addSeat((newSeat));

                        int row = newSeat.getRow();
                        char letter = newSeat.getLetter();
                        double price = newSeat.getPrice();

                        while (letter != 'f') {
                            while (row != 9) {
                                row++;
                                newSh.addSeat(new Seat(row, letter, price, true));
                            }
                            row = 0;
                            price += 25.0;
                            letter += 1;
                        }
                        // skip closing brace
                        in.readLine();
                        // read next line to either continue reading seats, or go back to reading
                        // showtimes
                        line = readItem();
                    }
                    // add showtime to theatre
                    newTh.addShowtime(newSh);

                    // adding multiple showtimes for multiple days
                    String date = newSh.getDate();
                    String[] breakD = date.split("/");
                    int day = Integer.parseInt(breakD[1]);
                    for (int i = 0; i < 5; i++) {
                        day = day + 2;
                        // copy constructor to be made
                        newTh.addShowtime(new Showtime(newSh.getStartTime(), newSh.getEndTime(),
                                "12/" + (day) + "/2020", newSh.getSeats(), newM));
                    }

                    // either continue reading more showtimes, or go back to reading theatres
                    line = readItem();
                }
                // add theatre to movie
                newM.addTheatre(newTh);
                // either continue reading theatres, or go back to reading movies
                line = readItem();
            }
            // add movie to database
            movies.add(newM);
            // either continue reading movies, or continue reading other database variables
            line = readItem();

            if (line == null) {
                break;
            }
        }

        System.out.println("Database read complete");

        in.close();

        // new input reader

        in = new BufferedReader(new FileReader(path));

        line = in.readLine();
        // read all stored tickets
        while (!line.equals("Tickets:"))
            line = readItem();

        line = readItem();
        while (line.equals("{")) {
            readItem();
            User newU = new User(readItem(), readItem(), readItem(), readItem());
            readItem();
            // user, ticketNo, movie, theatre, showtime, date, seat, price, isCredit
            int tickNo = readItem(0);
            String movName = readItem();
            String theName = readItem();
            String shoTime = readItem();
            String dateS = readItem();
            String seat = readItem();
            double priceS = readItem(0.0);

            // updating seat status
            for (Movie m : movies) {
                if (m.getName().equals(movName)) {
                    for (Theatre t : m.getTheatres()) {
                        if (t.getName().equals(theName)) {
                            for (Showtime s : t.getShowtimes()) {
                                String[] sTimes = shoTime.split(" ");
                                if ((sTimes[0] + " am").equals(s.getStartTime())) {
                                    if (dateS.equals(s.getDate())) {
                                        for (Seat st : s.getSeats()) {
                                            if (st.getRow() == Integer.parseInt(seat.charAt(0) + "")
                                                    && st.getLetter() == seat.charAt(1)) {
                                                st.setAvailable(false);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Receipt newR = new Receipt(newU, tickNo, movName, theName, shoTime, dateS, seat, priceS, readItem(true));
            tickets.add(newR);
            readItem();
            line = readItem();
        }

        // read all registered users
        while (!line.equals("Registered Users:"))
            line = readItem();

        line = readItem();
        while (line.equals("{")) {
            RegisteredUser newRU = new RegisteredUser(readItem(), readItem(), readItem(), readItem(), true);
            registered_users.add(newRU);
            readItem();
            line = readItem();
        }

        // read the current ticket number
        while (!line.equals("Current Ticket Number:"))
            line = readItem();
        currentTicketNum = readItem(0);

        in.close();
    }

    /** helper function for data parsing process */
    private String readItem() throws IOException {
        String s = in.readLine();
        if (s == null)
            return null;
        return s.trim();
    }

    private int readItem(int i) throws IOException {
        return Integer.parseInt(in.readLine().replaceAll("\\s+", ""));
    }

    private double readItem(double i) throws IOException {
        return Double.parseDouble(in.readLine().replaceAll("\\s+", ""));
    }

    private boolean readItem(boolean b) throws IOException {
        return Integer.parseInt(in.readLine().replaceAll("\\s+", "")) > 0;
    }

    /** saves database contents to file */
    public void save() {
        try {
            FileWriter out = new FileWriter(path);
            /*
             * // write Movies header out.write("Movies:\n"); // write all movies for (Movie
             * m : movies) { out.write("{\n"); out.write("    " + m.getName() + "\n"); //
             * write all theatres in movie for (Theatre t : m.getTheatres()) {
             * out.write("    {\n"); out.write("        " + t.getName() + "\n");
             * out.write("        " + t.getAddress() + "\n"); // write all showtimes in
             * theatre for (Showtime sh : t.getShowtimes()) { out.write("        {\n");
             * out.write("            " + sh.getStartTime() + "\n");
             * out.write("            " + sh.getEndTime() + "\n"); // write all seats in
             * showtime for (Seat s : sh.getSeats()) { out.write("            {\n");
             * out.write("                " + s.getRow() + "\n");
             * out.write("                " + s.getLetter() + "\n");
             * out.write("                " + s.getPrice() + "\n"); if (s.isAvailable())
             * out.write("                1\n"); else out.write("                0\n");
             * out.write("            }\n"); } out.write("        }\n"); }
             * out.write("    }\n"); } out.write("}\n"); }
             */
            // write tickets header
            out.write("\nTickets:\n");
            // write all tickets
            for (Receipt r : tickets) {
                out.write("{\n");
                writeUser(out, r.getBuyer(), "    ");
                out.write("    " + r.getNum() + "\n");
                out.write("    " + r.getMovieTitle() + "\n");
                out.write("    " + r.getTheatreName() + "\n");
                out.write("    " + r.getShowtime() + "\n");
                out.write("    " + r.getDate() + "\n");
                out.write("    " + r.getSeat() + "\n");
                out.write("    " + r.getPrice() + "\n");
                if (r.isCredit())
                    out.write("    1\n");
                else
                    out.write("    0\n");
                out.write("}\n");
            }

            // write registered users header
            out.write("\nRegistered Users:\n");
            // write all registered users
            for (RegisteredUser ru : registered_users)
                writeUser(out, ru, "");

            // write current ticket number header and value
            out.write("\nCurrent Ticket Number:\n");
            out.write("" + currentTicketNum);

            out.close();
        } catch (IOException e) {
            System.out.println("Error saving database to file\n" + e.getMessage());
        }
    }

    /** helper function for save */
    private void writeUser(FileWriter out, User u, String lead) throws IOException {
        out.write(lead + "{\n");
        out.write(lead + "    " + u.getFirstName() + "\n");
        out.write(lead + "    " + u.getLastName() + "\n");
        out.write(lead + "    " + u.getAddress() + "\n");
        out.write(lead + "    " + u.getEmail() + "\n");
        out.write(lead + "}\n");
    }

    /** prints out database contents for testing purposes */
    public void print() {
        System.out.println("\nMovies:");
        for (Movie m : movies) {
            System.out.println(m);
            for (Theatre t : m.getTheatres()) {
                System.out.println("  " + t);
                for (Showtime sh : t.getShowtimes()) {
                    System.out.println("    " + sh);
                    for (Seat s : sh.getSeats())
                        System.out.println("      " + s);
                }
            }
        }

        System.out.println("\nTickets:");
        for (Receipt r : tickets) {
            System.out.println(r);
        }
        System.out.println("\nRegistered Users:");
        for (RegisteredUser ru : registered_users) {
            System.out.println(ru);
        }
        System.out.println("\nCurrent Ticket Number:\n" + currentTicketNum);
    }

    public ArrayList<Receipt> getTicket() {
        return tickets;
    }

    public ArrayList<RegisteredUser> getUsers() {
        return registered_users;
    }

    public int getTicketNum() {
        return currentTicketNum++;
    }

    public Movie findMovie(String s) {
        for (Movie m : movies) {
            if (m.getName().toLowerCase().equals(s.toLowerCase()))
                return m;
        }
        return null;
    }

    public Movie getMovieAtIndex(int n) {
        if (n >= movies.size())
            return null;
        return movies.get(n);
    }

    public Receipt findTicket(int num) {
        for (Receipt r : tickets) {
            if (r.getNum() == num)
                return r;
        }
        return null;
    }

    public boolean isRegistered(RegisteredUser userToCheck) {
        for (RegisteredUser ru : registered_users)
            if (ru.equals(userToCheck))
                return true;

        return false;
    }

    public ArrayList<String> getMovieNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Movie m : movies)
            names.add(m.getName());
        return names;
    }

    public void openUpSeat(Receipt r) {
        Movie movie = findMovie(r.getMovieTitle());
        if (movie == null) {
            System.out.println("Movie not Found");
            return;
        }

        Theatre theatre = null;
        for (Theatre t : movie.getTheatres()) {
            if (t.getName().equals(r.getTheatreName())) {
                theatre = t;
                break;
            }
        }
        if (theatre == null) {
            System.out.println("Theatre not Found");
            return;
        }

        Showtime showtime = null;
        for (Showtime s : theatre.getShowtimes()) {
            if ((s.getStartTime() + " to " + s.getEndTime()).equals(r.getShowtime())
                    && s.getDate().equals(r.getDate())) {
                showtime = s;
                break;
            }
        }
        if (showtime == null) {
            System.out.println("Showtime not Found");
            return;
        }

        for (Seat s : showtime.getSeats()) {
            String data = s.getRow() + s.getLetter() + "";
            System.out.println("Database :" + s.getRow() + s.getLetter() + "" + "| " + (int) data.charAt(0) + " "
                    + (int) data.charAt(1));
            System.out.println(
                    "Receipt :" + r.getSeat() + "| " + (int) r.getSeat().charAt(0) + " " + (int) r.getSeat().charAt(1));
            if ((s.getRow() + "" + s.getLetter() + "").replaceAll("\\s", "")
                    .equals(r.getSeat().replaceAll("\\s", ""))) {
                s.setAvailable(true);
                save();
                return;
            }
        }
        System.out.println("Seat not Found");
    }

    public static void main(String[] args) {
        Database db = new Database();
        try {
            db = new Database("model/data.txt");
        } catch (Exception e) {
            System.out.println("Error reading from database file\n" + e.getMessage());
        }

        // db.print();
        db.save();
    }
}