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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Name : " + firstName + ", " + lastName + "\nAddress : " + address + "\nEmail : " + email;
    }

}