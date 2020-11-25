package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import control.*;
import jdk.nashorn.internal.scripts.JO;

public class UserInterface extends JFrame {
    JButton select_movie, choose_seat, purchase_ticket, refund_ticket, register;
    JPanel title_panel, button_panel, logout_panel;
    JTextArea jta;
    JScrollPane display;

    UserInterfaceController control;

    public UserInterface(UserInterfaceController control) {
        this.control = control;

        jta = new JTextArea("Welcome!!");
        jta.setMargin(new Insets(3, 7, 3, 5));
        jta.setEditable(false);

        setTitle("Ticket Booking System");
        setSize(600, 400);

        setLayout(new GridLayout(2, 1));

        JScrollPane display = new JScrollPane(jta);

        add(display);
        add(addButtons());
    }

    private JPanel addButtons() {
        // Creating Button Panel button_panel = new JPanel();
        button_panel = new JPanel();
        button_panel.setLayout(new BoxLayout(button_panel, 1));

        // Creating buttons
        select_movie = new JButton("Select Movie");
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
            control.getMenuOption();
        });
        choose_seat.addActionListener((ActionEvent e) -> {
            control.getSeat();
        });
        purchase_ticket.addActionListener((ActionEvent e) -> {
            control.startPurchase();
        });
        refund_ticket.addActionListener((ActionEvent e) -> {
            control.startRefund();
        });
        register.addActionListener((ActionEvent e) -> {
            String fName, lName, add, email;
            fName = JOptionPane.showInputDialog(null, "Enter your first name: ");
            lName = JOptionPane.showInputDialog(null, "Enter your last name: ");
            add = JOptionPane.showInputDialog(null, "Enter your address");
            email = JOptionPane.showInputDialog(null, "Enter your email id: ");

            control.getUserInfo(fName, lName, add, email);
        });

        return button_panel;
    }
}