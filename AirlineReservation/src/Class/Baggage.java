package Class;
/**
 * The Baggage class is used to keep track of a passenger's baggage amount.
 *
 * @since 03/27/2023
 * @author Carlos Figueroa (developed class) and Ana Emily Castillo Perez (added documentation comments).
 * <p>
 * <b>Explanation of important functions:</b> Relates a specific passenger with a certain amount of bags.
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

public class Baggage {
    private String passengerName;
    private int numBags;


    public Baggage() {

    }

    /**
     *
     * Method for storing baggage properties.
     *
     * @param passengerName passengerName
     * @param numBags numBags
     *
     */
    public Baggage(String passengerName, int numBags) {
        this.passengerName = passengerName;
        this.numBags = numBags;
    }

    /**
     *
     * For obtaining a passenger's name.
     *
     * @return passengerName
     *
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     *
     * For obtaining the passenger's number of bags.
     *
     * @return numBags
     *
     */
    public int getNumBags() {
        return numBags;
    }

    /**
     *
     * For setting a passenger's name.
     *
     * @param passengerName passengerName
     *
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     *
     * For setting a passenger's number of bags.
     *
     * @param numBags numBags
     *
     */
    public void setNumBags(int numBags) {
        this.numBags = numBags;
    }
}
