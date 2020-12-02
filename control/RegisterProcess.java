package control;
import java.util.ArrayList;

public class RegisterProcess{
    ArrayList<String> userInfo;
    RegisteredUser user;

    public RegisterProcess(ArrayList<String> uInfo){
        userInfo = uInfo;
        if(userInfo.get(4).equals("t")){
        user = new RegisteredUser(userInfo.get(0),userInfo.get(1), userInfo.get(2), userInfo.get(3), true, Integer.parseInt(userInfo.get(4)));}
        else{
            user = new RegisteredUser(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3), false, Integer.parseInt(userInfo.get(4)));
        }
    }

    //uses the DatabaseController to add the user to the list of RegisteredUsers in the database
    public void addRegisteredUser(){
        DatabaseController.getOnlyInstance().addRegUser(user);
    }

    //NOT FULLY IMPLEMENTED
    //would email the user confirmation of their registration
    public void emailUser(){

    }

    //NOT FULLY IMPLEMENTED
    // would complete the payment for the ticket and use the user's credit/debit card info
    public void paymentAPI(){
        
    }
}