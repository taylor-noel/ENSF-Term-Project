package control;

import model.Showtime;

public class RefundProcess {

    String dateCurrent;

    public RefundProcess() {
        dateCurrent = "12/4/2020";
    }

    public boolean modifyReciept(int num, boolean registered) {
        if (!checkDate(num)) {
            return false;
        }
        return DatabaseController.getOnlyInstance().modifyTicket(num, registered);
    }

    public boolean checkDate(int num) {

        Receipt rec = DatabaseController.getOnlyInstance().getTicket(num);

        String[] sepDate = rec.getDate().split("/");

        if ((Integer.parseInt(sepDate[1]) - 3) < 4) {
            return false;
        }

        return true;
    }
}