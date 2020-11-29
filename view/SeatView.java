package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeatView extends JFrame {

    JPanel seatDisp;
    JLabel screen;
    JFrame seats;

    public SeatView() {
        seats = new JFrame();
        seats.setSize(750, 600);
        seats.setTitle("Select a suitable seat");

        screen = new JLabel("Screen is here");
        screen.setBackground(Color.BLACK);
        screen.setAlignmentX(CENTER_ALIGNMENT);

        makeSeats();
        seats.add("Center", seatDisp);
        seats.add("North", screen);
        seats.setVisible(true);
    }

    public void makeSeats() {
        // asfe
        seatDisp = new JPanel();
    }

    public static void main(String[] args) {
        System.out.println("HI");
        new SeatView();
    }
}
