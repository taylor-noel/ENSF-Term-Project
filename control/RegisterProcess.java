package control;
import java.util.ArrayList;

public class RegisterProcess{
    ArrayList<String> userInfo;
    RegisteredUser user;

    public RegisterProcess(ArrayList<String> uInfo){
        userInfo = uInfo;
        if(userInfo.get(4).equals("t")){
        user = new RegisteredUser(userInfo.get(0),userInfo.get(1), userInfo.get(2), userInfo.get(3), true);}
        else{
            user = new RegisteredUser(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3), false);
        }
    }

    public void addRegisteredUser(){
        DatabaseController.getOnlyInstance().addRegUser(user);
    }

    public void emailUser(){

    }

    public void paymentAPI(){
        
    }
}