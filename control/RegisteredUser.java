package control;

public class RegisteredUser extends User{
    private boolean isRegistered;

    public RegisteredUser(String first, String last, String add, String email, boolean isR){
        super (first, last, add, email);
        isRegistered = isR;

    }

    public boolean isRegistered(){
        return isRegistered;
    }
}
