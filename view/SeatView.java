package view;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SeatView extends JFrame {

    static final long serialVersionUID = 1;
    JPanel seatDisp;
    JLabel screen;
    JFrame seats;
    String selected;
    private ArrayList<String> seatsUI;
    String[] st;

    public SeatView(/* ArrayList<String> seatInfo */) {
        try {
            seatsUI = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                seatsUI.add(i + " a 150.0 true");
            }
            for (int i = 0; i < 10; i++) {
                seatsUI.add(i + " b 150.0 false");
            }

            seats = new JFrame();
            seats.setBackground(Color.BLACK);
            seats.setSize(750, 600);
            seats.setTitle("Select a suitable seat");

            seatDisp = new JPanel();
            seatDisp.setBackground(Color.GRAY);
            seatDisp.setAlignmentX(CENTER_ALIGNMENT);
            seatDisp.setAlignmentY(CENTER_ALIGNMENT);

            screen = new JLabel("Screen is here");
            screen.setBackground(Color.BLACK);
            screen.setForeground(Color.WHITE);
            screen.setAlignmentX(CENTER_ALIGNMENT);
            screen.setAlignmentY(CENTER_ALIGNMENT);

            // seatsUI=seatInfo;
            seats.add("Center", seatDisp);
            seats.add("North", screen);
            seats.setVisible(true);
            makeSeats();
        } catch (Exception e) {
            // skip
        }
    }

    public void makeSeats() throws Exception {
        int i = 0;
        st = seatsUI.get(i).split(" ");
        String row = st[0];

        while (i < seatsUI.size()) {
            row = st[0];
            JButton jb = new JButton(row + st[1]);
            if (st[3].equals("true")) {
                jb.setBackground(Color.GREEN);
            } else if (st[3].equals("false")) {
                jb.setBackground(Color.RED);
            }

            jb.addActionListener((ActionEvent e) -> {
                selected = (jb.getText());
                System.out.println(selected);
            });
            seatDisp.add(jb);

            i++;
            st = seatsUI.get(i).split(" ");
        }
    }

    public String getSeat() {
        return selected;
    }

    public static void main(String[] args) {
        System.out.println("HI");
        new SeatView();
    }
}
