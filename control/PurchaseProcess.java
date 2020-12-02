package control;

import java.util.ArrayList;

public class PurchaseProcess {
    private ArrayList<String> ticketInfo;
    private ArrayList<String> userInfo;
    private Receipt new_receipt;

    public PurchaseProcess(ArrayList<String> ticket, ArrayList<String> user) {
        ticketInfo = new ArrayList<String>(ticket);
        userInfo = new ArrayList<String>(user);
    }

    //creates a new receipt based on the userInfo and ticketInfo
    public void createReceipt() {
        User current_user = new User(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3));
        int ticketNumber = DatabaseController.getOnlyInstance().getTicketNumber();

        // user, ticketNo, movie, theatre, showtime, date, seat, price, isCredit
        new_receipt = new Receipt(current_user, ticketNumber, ticketInfo.get(0), ticketInfo.get(1), ticketInfo.get(2),
                ticketInfo.get(3), ticketInfo.get(4), Double.parseDouble(ticketInfo.get(5)), false);
    }

    //uses the databaseController to add the new_receipt to the list of receipts in the database
    public void addReciept() {
        DatabaseController.getOnlyInstance().addTicket(new_receipt);
    }

    //NOT FULLY IMPLEMENTED
    //should email the user with their receipt
    public void emailUser() {
        String email = userInfo.get(3);
        String toSend = "Thank you for purchasing a ticket! This is a copy of your receipt: \n"
                + new_receipt.toString();
        String from = "moviereg480@gmail.com";
        String host = "localhost";
        // emial user [not implemented]
    }
    
    //returns the receipt, new_receipt
    public Receipt getReceipt() {
        return new_receipt;
    }
}