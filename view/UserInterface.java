package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
//import control.UserInterfaceController;

public class UserInterface extends JFrame {
    JButton select_movie, choose_seat, purchase_ticket, refund_ticket, register;
    JLabel title;
    JPanel title_panel, button_panel;

    JScrollPane display;
    JTextArea ta;

    // UserInterfaceController control;

    public UserInterface(/* UserInterfaceController control */) {
        // this.control = control;

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
        // control.getUserInfo();
        for (String s : getUserInfo())
            System.out.println(s);

    }

    /**
     * Returns the information of user back to UserInterFaceController
     * 
     * @return
     */
    private ArrayList<String> getUserInfo() {
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
    private void getButtonPanel() {
        button_panel = new JPanel();

        // Creating buttons
        select_movie = new JButton("Search Movie");
        select_movie.setForeground(Color.white);
        select_movie.setBackground(Color.gray);
        select_movie.setFocusPainted(false);
        select_movie.setVisible(true);

        choose_seat = new JButton("Choose Seat");
        choose_seat.setForeground(Color.white);
        choose_seat.setBackground(Color.gray);
        choose_seat.setFocusPainted(false);
        choose_seat.setVisible(true);

        purchase_ticket = new JButton("Purchase Ticket");
        purchase_ticket.setForeground(Color.white);
        purchase_ticket.setBackground(Color.gray);
        purchase_ticket.setFocusPainted(false);
        purchase_ticket.setVisible(true);

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
            // control.getMovie();
        });
        choose_seat.addActionListener((ActionEvent e) -> {
            // control.getSeat();
        });
        purchase_ticket.addActionListener((ActionEvent e) -> {
            // control.purchaseTicket();
        });
        refund_ticket.addActionListener((ActionEvent e) -> {
            // control.refundTicket();
        });
        register.addActionListener((ActionEvent e) -> {
         userinfo.add("t");
            // control.registerUser();
        });

        // add buttons to button_panel
        button_panel.add(select_movie);
        button_panel.add(choose_seat);
        button_panel.add(purchase_ticket);
        button_panel.add(refund_ticket);
        button_panel.add(register);
    }

    /**
     * Creates the JPanel for showing title
     */
    private void getTitlePanel() {
        title_panel = new JPanel();
        title = new JLabel("Welcome!");
        title.setForeground(Color.white);
        title.setBackground(Color.gray);
        title_panel.add(title);
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
    }

    public static void main(String[] args) {
        System.out.println("HI");
        // UserInterfaceController control = new UserInterfaceController();
        new UserInterface();
    }
}
