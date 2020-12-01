package view;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SeatView extends JFrame {

    private static final long serialVersionUID = 1L;
    JPanel seatDisp, screen;
    JFrame seats;

    UserInterface UI;

    private ArrayList<String> seatsUI;

    String selected;
    String[] st;

    public SeatView(ArrayList<String> seatInfo, UserInterface ui) {
        UI = ui;
        try {
            seatsUI = seatInfo;
            seats = new JFrame();
            seats.setBackground(Color.BLACK);
            seats.setSize(750, 600);
            seats.setTitle("Select a suitable seat");
            seats.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            seatDisp = new JPanel();

            seatDisp.setBackground(Color.GRAY);
            seatDisp.setAlignmentX(CENTER_ALIGNMENT);
            seatDisp.setAlignmentY(CENTER_ALIGNMENT);

            screen = new JPanel();
            screen.setBackground(Color.CYAN);
            screen.setForeground(Color.WHITE);
            screen.setAlignmentX(CENTER_ALIGNMENT);
            screen.setAlignmentY(CENTER_ALIGNMENT);
            screen.add(new JLabel("Screen is here!! ", 0));

            JPanel exit = new JPanel();
            exit.setBackground(Color.YELLOW);
            exit.setAlignmentX(CENTER_ALIGNMENT);
            exit.setAlignmentY(CENTER_ALIGNMENT);
            exit.add(new JLabel("Doors!!", 0));

            // seatsUI=seatInfo;
            seats.add("Center", seatDisp);
            seats.add("North", screen);
            seats.add("South", exit);
            seats.setVisible(true);
            makeSeats();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeSeats() throws Exception {
        int i = 0;
        String row;

        int rowN = 0;
        char colN = 'a';

        for (String s : seatsUI) {
            st = seatsUI.get(i).split(" ");
            int r = Integer.parseInt(st[0]);
            if (rowN < r) {
                rowN = r;
            }
            if (colN < st[1].charAt(0)) {
                colN = st[1].charAt(0);
            }
        }

        int colNp = colN - 'a' + 1;

        seatDisp.setLayout(new GridLayout(rowN, colNp));

        JPanel jp = new JPanel();

        while (i < seatsUI.size()) {
            st = seatsUI.get(i).split(" ");
            row = st[0];

            if (row.equals("1")) {
                jp = new JPanel();
                seatDisp.add(jp);
            }

            JButton jb = new JButton(row + st[1]);
            if (st[3].equals("true")) {
                jb.setBackground(Color.GREEN);
            } else if (st[3].equals("false")) {
                jb.setBackground(Color.RED);
            }

            String text = jb.getText();
            jb.addMouseListener(new java.awt.event.MouseAdapter() {
                String price = st[2];

                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jb.setText(price);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jb.setText(text);
                }
            });

            jb.addActionListener((ActionEvent e) -> {
                jb.setText(text);
                if (Color.RED == jb.getBackground()) {
                    JOptionPane.showMessageDialog(null, "Seat Unavailable");
                } else if (Color.GREEN == jb.getBackground()) {
                    selected = (jb.getText());
                    int conf = JOptionPane.showConfirmDialog(null,
                            "You have selected " + selected
                                    + " as your seat. Press OK to purchase, Cancel to choose seat again",
                            "Confirm", JOptionPane.OK_CANCEL_OPTION);

                    if (conf == JOptionPane.OK_OPTION) {
                        // send selected option back to UI controller
                        UI.purchaseTicketUI(selected);
                        seats.dispose();
                        return;
                    }
                }
            });

            jp.add(jb);
            i++;
        }
    }

    public String getSeat() {
        // System.out.println(selected + " in seatV");
        return selected;
    }
}
