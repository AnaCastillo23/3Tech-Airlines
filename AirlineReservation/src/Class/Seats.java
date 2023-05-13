package Class;

import java.util.ArrayList;
/**
 * The Seats class is used to keep track of a passenger's reserved seats.
 *
 * @since 03/27/2023
 * @author Carlos Figueroa (developed class) and Ana Emily Castillo Perez (added documentation comments).
 * <p>
 * <b>Explanation of important functions:</b> .
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>Baggage(String passengerName, int numBags):</i> used to store baggage properties.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class Seats {
    private ArrayList<String> reservedSeatNumbers;
    private ArrayList<String> seatClasses;

    public Seats() {
        this.reservedSeatNumbers = null;
        this.seatClasses = null;
    }

    /**
     *
     * Method for storing reserved seat numbers.
     *
     * @param reservedSeatNumbers reservedSeatNumbers
     * @param seatClasses seatClasses
     *
     */
    public Seats(ArrayList<String> reservedSeatNumbers, ArrayList<String> seatClasses) {
        this.reservedSeatNumbers = reservedSeatNumbers;
        this.seatClasses = null;
    }

    /**
     *
     * For obtaining reserved seat numbers.
     *
     * @return reservedSeatNumbers
     *
     */
    public ArrayList<String> getReservedSeatNumbers() {
        return reservedSeatNumbers;
    }

    /**
     *
     * For obtaining the seats classes.
     *
     * @return seatClasses
     *
     */
    public ArrayList<String> getSeatClasses() {
        return seatClasses;
    }

    /**
     *
     * For setting reserved seat numbers.
     *
     * @param reservedSeatNumbers reservedSeatNumbers
     *
     */
    public void setReservedSeatNumbers(ArrayList<String> reservedSeatNumbers) {
        this.reservedSeatNumbers = reservedSeatNumbers;
    }

    /**
     *
     * For setting seats classes.
     *
     * @param seatClasses seatClasses
     *
     */
    public void setSeatClasses(ArrayList<String> seatClasses) {
        this.seatClasses = seatClasses;
    }
}
