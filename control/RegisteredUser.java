package control;

public class RegisteredUser extends User{
    private boolean isRegistered;

    public RegisteredUser(String first, String last, String add, String email, boolean b){
        super (first, last, add, email);
        isRegistered = b;
    }

    public boolean isRegistered(){
        return isRegistered;
    }

    public String toString(){
        return super.toString();
    }

    public boolean equals(RegisteredUser ru){
        return firstName.equals(ru.getEmail()) && lastName.equals(ru.getLastName()) &&
            address.equals(ru.getAddress()) && email.equals(ru.getEmail());
    }
}
