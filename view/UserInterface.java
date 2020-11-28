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

    // UserInterfaceController control;

    public UserInterface (/*TEST UserInterfaceController control */) {
        try{
        //TEST this.control = control;

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
        //TEST control.getUserInfo();

        for (String s : getUserInfo())
            System.out.println(s);
        
        } catch (Exception e) {
          e.printStackTrace();
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

        // asks user to check whether or not they are registered
        // asks to register if they are not already registered.
        // sets 5th arrayList element either f(false), t(true)
        try {
            int res = JOptionPane.showConfirmDialog(null, "Are you a registered user?", "User Login",
                    JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.NO_OPTION) {
                if (JOptionPane.showConfirmDialog(null, "Would you like to register?", "User Login",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    userInfo.add("f");
                } else {
                    ta.setText("\nPlease press register button at the bottom...");
                }
            } else {
                userInfo.add("t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    /**
     * Create the JPanel for showing buttons
     */
    private void getButtonPanel() throws Exception{
        button_panel = new JPanel();

        // Creating buttons
        select_movie = new JButton("Search Movie");
        select_movie.setForeground(Color.white);
        select_movie.setBackground(Color.gray);
        select_movie.setFocusPainted(false);
        select_movie.setVisible(true);

        //this button is being used to show different things one after other
        //first to see all theatres and then showtimes and then seats
        miscB = new JButton("Choose Seat");
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
        });
        miscB.addActionListener((ActionEvent e) -> {
           //checkTheatres();
        });
        purchase_ticket.addActionListener((ActionEvent e) -> {
            // control.purchaseTicket();
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

    public void searchMov(){
         String movName = JOptionPane.showInputDialog(null,"Please enter the name of the movie:");
            System.out.println(movName);

            //obtain list of movie title strings from database 
            //display list on the GUI 

          //  boolean res = control.setMovie(movName);
           /* if(res == false){
                ta.setText("Sorry, we can not find the movie...\n\nError 404\n\nPlease try again!");
            }
            else{*/
                select_movie.setText("Search new Movie");
               
                //Set 2nd button to show all theatres
                miscB.setText("Find all Theatres");
                miscB.setVisible(true);
           // }
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
    }
     

    public void updateScrollPanel(){
        
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
