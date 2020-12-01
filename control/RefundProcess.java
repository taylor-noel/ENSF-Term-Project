package control;

public class RefundProcess{

    public RefundProcess(){}

    public boolean modifyReciept(int num, boolean registered){
        return DatabaseController.getOnlyInstance().modifyTicket(num, registered);
    }
}