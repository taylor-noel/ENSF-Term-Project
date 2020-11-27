package control;

public class RefundProcess{

    public RefundProcess(){}

    public void modifyReciept(int num){
       DatabaseController.getOnlyInstance().modifyTicket(num);
    }
}