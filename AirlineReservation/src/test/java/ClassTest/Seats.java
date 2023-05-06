package ClassTest;

import java.util.ArrayList;

public class Seats {
    private ArrayList<String> reservedSeatNumbers;
    private ArrayList<String> flightTypes;

    public Seats() {
        this.reservedSeatNumbers = null;
        this.flightTypes = null;
    }

    public Seats(ArrayList<String> reservedSeatNumbers, ArrayList<String> flightTypes) {
        this.reservedSeatNumbers = reservedSeatNumbers;
        this.flightTypes = flightTypes;
    }

    public ArrayList<String> getReservedSeatNumbers() {
        return reservedSeatNumbers;
    }

    public ArrayList<String> getFlightTypes() {
        return flightTypes;
    }

    public void setReservedSeatNumbers(ArrayList<String> reservedSeatNumbers) {
        this.reservedSeatNumbers = reservedSeatNumbers;
    }

    public void setFlightTypes(ArrayList<String> flightTypes) {
        this.flightTypes = flightTypes;
    }
}
