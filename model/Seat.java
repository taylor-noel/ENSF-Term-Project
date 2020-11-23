package model;

public class Seat {
    private int row;
    private char letter;
    private double price;
    private boolean available;

    public Seat(int r, char l, double p, boolean a){
        row = r;
        letter = l;
        price = p;
        available = a;
    }

    public int getRow(){
        return row;
    }

    public char getLetter(){
        return letter;
    }

    public double getPrice(){
        return price;
    }

    public boolean isAvailable(){
        return available;
    }
}
