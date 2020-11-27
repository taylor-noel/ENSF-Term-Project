package control;

public class User{
    private String firstName;
    private String lastName;
    private String address;
    private String email;

    public User(String first, String last, String add, String e){
        firstName = first;
        lastName = last;
        address = add;
        email = e;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public String getEmail(){
        return email;
    }

    public String toString(){
        return "Name : " + firstName + ", " + lastName + " Address : " + address + " Email : " + email;
    }
    
}