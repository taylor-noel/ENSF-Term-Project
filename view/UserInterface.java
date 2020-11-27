package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
//import control.UserInterfaceController;

public class UserInterface  extends JFrame{
    JButton select_movie, choose_seat, purchase_ticket, refund_ticket, register; 
    JLabel title;
    JPanel title_panel, button_panel;

    JScrollPane display;

    //UserInterfaceController control; 

    public UserInterface(/*UserInterfaceController control*/){
        //this.control = control; 
        
        //Creating Button Panel 
        button_panel = new JPanel();

        //Creating buttons 
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

        //Adding Button Listeners 
        select_movie.addActionListener((ActionEvent e)->{
        	//control.getMovie();
        });
        choose_seat.addActionListener((ActionEvent e)->{
        	//control.getSeat();
        });
        purchase_ticket.addActionListener((ActionEvent e)->{
        	//control.purchaseTicket();
        });
        refund_ticket.addActionListener((ActionEvent e)->{
        	//control.refundTicket();
        });
        register.addActionListener((ActionEvent e)->{
        	//control.registerUser
        });
        
        //add buttons to button_panel
        button_panel.add(select_movie);
        button_panel.add(choose_seat);
        button_panel.add(purchase_ticket);
        button_panel.add(refund_ticket);
        button_panel.add(register);
        
        //Create title panel
        title_panel = new JPanel();
        title = new JLabel("Movie Reservation Application");
        title.setForeground(Color.white);
        title.setBackground(Color.gray);
        title_panel.add(title);
        title_panel.revalidate();
        
        //create display panel 
        display = new JScrollPane();
        JTextArea ta = new JTextArea("",35,62);
        ta.setForeground(Color.white);
        ta.setBackground(Color.gray);
        
        display = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        display.setBackground(Color.DARK_GRAY);
        
        this.add("South",button_panel);
        this.add("North", title_panel);
        this.add("Center",display);
        this.setSize(1080,650);
        this.setLocationRelativeTo(null);
        
        title_panel.setBackground(Color.DARK_GRAY);
        button_panel.setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
    	System.out.println("HI");
    	//UserInterfaceController control = new UserInterfaceController();
    	UserInterface UI = new UserInterface();
    	UI.setVisible(true);
    }
}


