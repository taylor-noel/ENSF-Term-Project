package control;
import java.util.ArrayList;


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
        int ticketNumber = 0;
        Receipt new_reciept = new Receipt(current_user, ticketNumber, ticketInfo[0], ticketInfo[1], ticketInfo[2], ticketInfo[3], Double.parseDouble(ticketInfo[4]),Boolean.parseBoolean(ticketInfo[5]));
    }

    public void addReciept(){
        DatabaseController.getOnlyInstance().addTicket(new_reciept);
    }

    public void emailUser(){
        String email = userInfo.get(3);
        String toSend = "Thank you for purchasing a ticket! This is a copy of your receipt: \n"+Receipt.toString(); 
        //sends email 
    }

    public void paymentAPI(){
        //ask UI to generate a popup to confirm payment 
    }
    //leaving this comment here so this doesnt duplicate itself!!! !!!
}ublic void createReceipt(){
        User current_user = new User(userInfo.get(0), userInfo.get(1),  userInfo.get(2), userInfo.get(3));
        int ticketNumber = 0;
        Receipt new_reciept = new Receipt(current_user, ticketNumber, ticketInfo[0], ticketInfo[1], ticketInfo[2], ticketInfo[3], Double.parseDouble(ticketInfo[4]),Boolean.parseBoolean(ticketInfo[5]));
    }

    public void addReciept(){

    }

    public void emailUser(){

    }

    public void paymentAPI(){
        
    }
}