package control;

public class RefundProcess{

    public RefundProcess(){}

    public void modifyReciept(int num, boolean registered){
       DatabaseController.getOnlyInstance().modifyTicket(num, registered);
    }
}