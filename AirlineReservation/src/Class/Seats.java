package Class;

import java.util.ArrayList;

public class Seats {
    private ArrayList<String> reservedSeatNumbers;
    private ArrayList<String> seatClasses;

    public Seats() {
        this.reservedSeatNumbers = null;
        this.seatClasses = null;
    }

    public Seats(ArrayList<String> reservedSeatNumbers, ArrayList<String> seatClasses) {
        this.reservedSeatNumbers = reservedSeatNumbers;
        this.seatClasses = null;
    }

    public ArrayList<String> getReservedSeatNumbers() {
        return reservedSeatNumbers;
    }

    public ArrayList<String> getSeatClasses() {
        return seatClasses;
    }

    public void setReservedSeatNumbers(ArrayList<String> reservedSeatNumbers) {
        this.reservedSeatNumbers = reservedSeatNumbers;
    }

    public void setSeatClasses(ArrayList<String> seatClasses) {
        this.seatClasses = seatClasses;
    }
}
