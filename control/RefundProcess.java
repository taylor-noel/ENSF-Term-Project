package control;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import model.Showtime;

public class RefundProcess {

    String dateCurrent;

    //sets the date, used for testing
    public RefundProcess() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        dateCurrent = dtf.format(now);
        dataCurrent = "12/1/2020";
    }

    //uses the DatabaseController to modify the receipt in the database 
    // and changes it to a credit
    public boolean modifyReciept(int num, boolean registered) {
        if (!checkDate(num)) {
            return false;
        }
        return DatabaseController.getOnlyInstance().modifyTicket(num, registered);
    }

    //checks to see if the date is within 3 days of the movie date to decide whether the refund
    //is allowed to go through
    public boolean checkDate(int num) {

        Receipt rec = DatabaseController.getOnlyInstance().getTicket(num);

        String[] sepDate = rec.getDate().split("/");

        if ((Integer.parseInt(sepDate[1]) - 3) < 4) {
            return false;
        }

        return true;
    }
}