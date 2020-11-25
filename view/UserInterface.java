package view;

public class UserInterface {
    Jbutton select_movie, choose_seat, purchase_ticket, refund_ticket, register; 
    JLabel title;
    JPanel title_panel, button_panel, logout_panel;

    JScrollPane display;

    UserInterfaceController control; 

    public UserInterface(UserInterfaceController control){
        this.control = control; 
        
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
        select_movie.addActionListener(ActionEvent e)->{

        }
        choose_seat.addActionListener(ActionEvent e)->{

        }
        purchase_ticket.addActionListener(ActionEvent e)->{

        }
        refund_ticket.addActionListener(ActionEvent e)->{

        }
        register.addActionListener(ActionEvent e)->{

        }
        
    }
}
