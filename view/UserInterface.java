package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
//import control.UserInterfaceController;

public class UserInterface extends JFrame {
    JButton select_movie, miscB, purchase_ticket, refund_ticket, register;
    JLabel title_label;
    JPanel title_panel, button_panel;

    JScrollPane display;
    JTextArea ta;

    char miscFlag; // flag to determine misc button status

    // UserInterfaceController control;

    public UserInterface(/* TEST UserInterfaceController control */) {
        try {
            // TEST this.control = control;

            // Creating Button Panel
            getButtonPanel();

            // Create title panel
            getTitlePanel();

            // create display panel
            getDisplayPanel();

            this.add("South", button_panel);
            this.add("North", title_panel);
            this.add("Center", display);
            this.setSize(1080, 650);
            this.setLocationRelativeTo(null);

            title_panel.setBackground(Color.DARK_GRAY);
            button_panel.setBackground(Color.DARK_GRAY);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setVisible(true);
            setTitle("Movie Registration Application");

            // getting user information before starting app
            // TEST control.getUserInfo();
            for (String s : getUserInfo())
                System.out.println(s);

            browseAllMovies();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays all the movies on the screen
     */
    public void browseAllMovies() {
        // ArrayList<String> movs = control.getMovies();

        // for testing
        ArrayList<String> movs = new ArrayList<>();
        movs.add("Mov 1");
        movs.add("Mov 2");
        // upto here

        ta.append("\n\nMovies available to see currently:\n\n\n\n\t");

        for (String mName : movs) {
            ta.append(mName);
            ta.append("\n\t");
        }
    }

    /**
     * Returns the information of user back to UserInterFaceController
     * 
     * @return user information to UIController
     */
    public ArrayList<String> getUserInfo() {
        ArrayList<String> userInfo = new ArrayList<>();
        try {
            userInfo.add(JOptionPane.showInputDialog(null, "Enter your first name:", "User Login",
                    JOptionPane.PLAIN_MESSAGE));
            userInfo.add(JOptionPane.showInputDialog(null, "Enter your last name:", "User Login",
                    JOptionPane.PLAIN_MESSAGE));
            userInfo.add(
                    JOptionPane.showInputDialog(null, "Enter your address:", "User Login", JOptionPane.PLAIN_MESSAGE));
            userInfo.add(JOptionPane.showInputDialog(null, "Enter your email address:", "User Login",
                    JOptionPane.PLAIN_MESSAGE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid name entered, Please enter a String", "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }

        // creates a registered used class
        // RegisteredUser ru = new RegisteredUser(userInfo[0], userInfo[1], userInfo[2],
        // userInfo[3], userInfo[4], true);

        // checks with database to see if user is actually registered
        // boolean registered = control.checkRegUser(ru);

        // if registered
        // if(registered){
        // set userInfo[5] = true
        // userInfo[5] = "true";
        // continue to menu
        // }else{
        // if not registered
        try {
            // display "Would you like to register?" message
            if (JOptionPane.showConfirmDialog(null, "Would you like to register?", "Register Prompy",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Please press the register button at bottom of the screen...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        userInfo.add("false");

        return userInfo;
    }

    /**
     * Create the JPanel for showing buttons
     */
    private void getButtonPanel() throws Exception {
        button_panel = new JPanel();

        // Creating buttons
        select_movie = new JButton("Select Movie");
        select_movie.setForeground(Color.white);
        select_movie.setBackground(Color.gray);
        select_movie.setFocusPainted(false);
        select_movie.setVisible(true);

        // this button is being used to show different things one after other
        // first to see all theatres and then showtimes and then seats
        miscB = new JButton();
        miscB.setForeground(Color.white);
        miscB.setBackground(Color.gray);
        miscB.setFocusPainted(false);
        miscB.setVisible(false);

        purchase_ticket = new JButton("Purchase Ticket");
        purchase_ticket.setForeground(Color.white);
        purchase_ticket.setBackground(Color.gray);
        purchase_ticket.setFocusPainted(false);
        purchase_ticket.setVisible(false);

        refund_ticket = new JButton("Refund Ticket");
        refund_ticket.setForeground(Color.white);
        refund_ticket.setBackground(Color.gray);
        refund_ticket.setFocusPainted(false);
        refund_ticket.setVisible(true);

        register = new JButton("Register");
        register.setForeground(Color.white);
        register.setBackground(Color.gray);
        register.setFocusPainted(false);
        register.setVisible(true);

        // Adding Button Listeners
        select_movie.addActionListener((ActionEvent e) -> {
            searchMov();
            showTheatres();
            // Set 2nd button to show all theatres
            miscB.setText("Select movie's Theatre");
            miscFlag = 't';
            miscB.setVisible(true);
        });
        miscB.addActionListener((ActionEvent e) -> {
            if (miscFlag == 't') {
                chooseTheatre();
                showShowTimes();
                miscB.setText("Select Showtime");
                miscFlag = 's';
            } else if (miscFlag == 's') {
                chooseShowTimes();
                miscB.setText("Select a Seat");
                miscFlag = 'f';
            } else if (miscFlag == 'f') {

                ArrayList<String> testSeats = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    testSeats.add(i + " a 150.0 true");
                }
                for (int i = 0; i < 10; i++) {
                    testSeats.add(i + " b 150.0 false");
                }

                SeatView sv = new SeatView(testSeats/* control.getAllSeats() */);

                System.out.println(sv.getSeat());
            }
        });
        purchase_ticket.addActionListener((ActionEvent e) -> {
            // String message = control.purchaseTicket();
            // ta.setText(message);
        });
        refund_ticket.addActionListener((ActionEvent e) -> {
            // control.refundTicket();
        });
        register.addActionListener((ActionEvent e) -> {
            // control.registerUser();
            register.setVisible(false);
        });

        // add buttons to button_panel
        button_panel.add(select_movie);
        button_panel.add(miscB);
        button_panel.add(purchase_ticket);
        button_panel.add(refund_ticket);
        button_panel.add(register);
    }

    public void chooseShowTimes() {
        String sTime = "";
        sTime = JOptionPane.showInputDialog(null, "Please enter the showTime:");
        System.out.println(sTime);
        // control.setTheatre(tName);

        ta.setText("You have selected " + sTime);
    }

    public void showShowTimes() {
        // ArrayList<String> sTimes = control.getShowtimes();

        // for testing
        ArrayList<String> sTimes = new ArrayList<>();
        sTimes.add("ST 1");
        sTimes.add("sT 2");
        // upto here
        for (String s : sTimes) {
            ta.append(s);
            ta.append("\n\t");
        }
    }

    /**
     * Helps in choosing the theatre
     */
    public void chooseTheatre() {
        String tName = "";
        tName = JOptionPane.showInputDialog(null, "Please enter the Theater's name, in which you want to watch movie:");
        System.out.println(tName);
        // control.setTheatre(tName);

        ta.setText("You have selected " + tName + ", \n\t Following are the available showtimes... \n\n\n\t");
    }

    /**
     * Helps user to search and select movie
     */
    public void searchMov() {
        String movName = JOptionPane.showInputDialog(null, "Please enter the name of the movie, you want to see:");
        System.out.println(movName);

        // obtain list of movie title strings from database
        // display list on the GUI

        // boolean res = false;// = control.setMovie(movName);

        // if (res == false) {
        // ta.setText("Sorry, we can not find the movie...\n\nError 404\n\nPlease try
        // again!");
        // browseAllMovies();
        // } else {
        select_movie.setText("Select a new Movie");

        ta.setText("You have selected to watch:\n\n\n\t" + movName
                + "\n\n\n\nPlease choose from following theatres...\n\n\t");
    }

    /**
     * Shows all the theatres available
     */
    public void showTheatres() {
        // ArrayList<String> tAvail = control.getTheatres();
        String tName = "";

        // for testing
        ArrayList<String> tAvail = new ArrayList<>();
        tAvail.add("Theatre 1");
        tAvail.add("Theatre 2");
        // upto here

        for (String t : tAvail) {
            ta.append(t);
            ta.append("\n\t");
        }
    }

    /**
     * Creates the JPanel for showing title
     */
    private void getTitlePanel() {
        title_panel = new JPanel();
        title_label = new JLabel("Welcome!");
        title_label.setForeground(Color.white);
        title_label.setBackground(Color.gray);
        title_panel.add(title_label);
        title_panel.revalidate();
    }

    /**
     * Creates the JPanel for showing Display area
     */
    private void getDisplayPanel() {
        display = new JScrollPane();

        ta = new JTextArea("", 35, 62);
        ta.setForeground(Color.white);
        ta.setBackground(Color.gray);

        ta.setText("\nPlease Enter your information to continue... ");
        ta.setEditable(false);

        display = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        display.setBackground(Color.DARK_GRAY);
        ta.setEditable(false);
    }

    public static void main(String[] args) {
        System.out.println("HI");
        // UserInterfaceController control = new UserInterfaceController();
        new UserInterface();
    }
}
