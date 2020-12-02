package control;

public class RegisteredUser extends User{
    private boolean isRegistered;
    int payment_account;    //credit/debit card number

    public RegisteredUser(String first, String last, String add, String email, boolean b, int account_number){
        super (first, last, add, email);
        isRegistered = b;
        payment_account = account_number;
    }

    //returns the boolean isRegistered
    public boolean isRegistered(){
        return isRegistered;
    }

    public String toString(){
        return super.toString();
    }

    //checks to see if this RegisteredUser is equal to ru
    public boolean equals(RegisteredUser ru){
        return firstName.equals(ru.getFirstName()) && lastName.equals(ru.getLastName()) &&
            address.equals(ru.getAddress()) && email.equals(ru.getEmail());
    }

    //returns the credit/debit card number of the registeredUser
    public int getAccount(){
        return payment_account;
    }
}
