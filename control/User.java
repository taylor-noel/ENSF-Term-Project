package control;

public class User {
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String email;

    public User(String first, String last, String add, String e) {
        firstName = first;
        lastName = last;
        address = add;
        email = e;
    }

    //returns the first name of the user
    public String getFirstName() {
        return firstName;
    }

    //returns the last name of the user
    public String getLastName() {
        return lastName;
    }

    //returns the address of the user
    public String getAddress() {
        return address;
    }

    //returns the email of the user
    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Name : " + firstName + ", " + lastName + "\nAddress : " + address + "\nEmail : " + email;
    }

}