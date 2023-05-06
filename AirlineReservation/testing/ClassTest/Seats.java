package ClassTest;

import java.util.ArrayList;

public class Seats {
    private ArrayList<String> reservedSeatNumbers;
    private ArrayList<String> seatClass;

    public Seats() {
        this.reservedSeatNumbers = null;
        this.seatClass = null;
    }

    public Seats(ArrayList<String> reservedSeatNumbers, ArrayList<String> seatClass) {
        this.reservedSeatNumbers = reservedSeatNumbers;
        this.seatClass = seatClass;
    }

    public ArrayList<String> getReservedSeatNumbers() {
        return reservedSeatNumbers;
    }

    public ArrayList<String> getSeatClass() {
        return seatClass;
    }

    public void setReservedSeatNumbers(ArrayList<String> reservedSeatNumbers) {
        this.reservedSeatNumbers = reservedSeatNumbers;
    }

    public void setSeatClass(ArrayList<String> seatClass) {
        this.seatClass = seatClass;
    }
}
