package control;
import java.util.ArrayList;
// import java.util.*;
// import javax.mail.*;
// import javax.mail.internet.*;
// import javax.activation.*;

public class PurchaseProcess{
    private ArrayList <String> ticketInfo;
    private ArrayList <String> userInfo;
    private Receipt new_receipt; 

    public PurchaseProcess(ArrayList<String> ticket, ArrayList<String> user){
        ticketInfo = new ArrayList<String>(ticket);
        userInfo =  new ArrayList<String>(user);
    }

    public void createReceipt(){
        User current_user = new User(userInfo.get(0), userInfo.get(1),  userInfo.get(2), userInfo.get(3));
        int ticketNumber = DatabaseController.getOnlyInstance().getTicketNumber();
        new_receipt = new Receipt(current_user, ticketNumber, ticketInfo.get(0), ticketInfo.get(1), ticketInfo.get(2), ticketInfo.get(3), Double.parseDouble(ticketInfo.get(4)), false);
    }

    public void addReciept(){
        DatabaseController.getOnlyInstance().addTicket(new_receipt);
    }

    public void emailUser(){
        String email = userInfo.get(3);
        String toSend = "Thank you for purchasing a ticket! This is a copy of your receipt: \n" + new_receipt.toString(); 
        String from = "kalikalikalikali@gmail.com"; 
        String host = "localhost";

        // Properties properties = System.getProperties();
        // properties.setProperty("mail.smtp.host", host);

        // Session session = Session.getDefaultInstance(properties);

        // try{
        //     MimeMessage message = new MimeMessage(session);
        //     message.setFrom(new InternetAddress(from));

        //     message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        //     message.setSubject("Movie Receipt");
        //     message.setText(toSend);

        //     Transport.send(message);

        // }catch (MessagingException mex){
        //     mex.printStackTrace();
        // }
     }

    public Receipt getReceipt(){
        return new_receipt;
    }
}