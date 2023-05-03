package Class;

import java.util.ArrayList;

/**
 * The Reservation class is used in conjunction with information recollected from
 * the API used in this project. All data concerning a booking will land in this class.
 * @since 03/27/2023
 * @author Ana Emily Castillo Perez (added to general structure of class), Carlos Figueroa (addde to general structure of class).
 * <p>
 * <b>Explanation of important functions:</b> Class implements user input entered during the FlightSearchFrame utilization
 * and after user has choosen their desired flight for booking. Such data concerning the choosen flights goes here
 * for later referencing. For example, class might be used in the future by the system to output recent flight bookings for
 * returning users.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>Reservation(int reservationID, Date departureDate, String departFlightNumber, String returnFlightNumber, int partySize, ArrayList<Passenger> party):</i> used for creating a new reservation for a one-way trip</li>
 * <li><i>Reservation(int reservationID, Date departureDate, Date returnDate, String departFlightNumber, String returnFlightNumber, int partySize, ArrayList<Passenger> party):</i> used for creating a new reserrvation for a round trip.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */

//Data init
public class Reservation {

    // helper class
    // one flight per destination - one way: one Flight class
    //                            - round trip: two Flight classes
    private Flight departureFlight;
    private Flight returnFlight;

    //private Passenger passenger;
    private ArrayList<Passenger> party;

    private int reservationID;
    private Double flightPrice;
    private Double flightTax;
    private Double flightTotal;
    private String departureDate;
    private String returnDate;
    private boolean roundTrip;
    private String departureFlightNumber;
    private String returnFlightNumber;
    private int departurePartySize;
    private int returnPartySize;
    public static ArrayList<Passenger> departureParty = new ArrayList<>();
    public static ArrayList<Passenger> returnParty = new ArrayList<>();


    /**
     *
     * Constructor that is empty, at the moment.
     *
     */
    public Reservation() {
        this.reservationID = 0;
        this.flightPrice = 0.0;
        this.flightTax = 0.0;
        this.flightTotal = 0.0;
        this.departureDate = null;
        this.returnDate = null;
        this.roundTrip = false;
        this.departureFlightNumber = null;
        this.returnFlightNumber = null;
        this.departurePartySize = 0;
        this.returnPartySize = 0;
        //this.departureParty = null;
        //this.returnParty = null;
    }

    /**
     *
     * Method used for creating a new reservation for a one-way trip. Information gathered from the API is stored here for later
     * retrieval by system (allows returning users to check their recent reservation).
     *
     * @param reservationID reservationID
     * @param departureDate departureDate
     * @param departureFlightNumber departFlightNumber
     * @param departurePartySize departurePartySize
     * @param departureParty departureParty
     *
     */
    // one way trip
    public Reservation(int reservationID, Double flightPrice, Double flightTax, Double flightTotal, String departureDate, String departureFlightNumber, int departurePartySize, ArrayList<Passenger> departureParty) {
        this.reservationID = reservationID;
        this.flightPrice = flightPrice;
        this.flightTax = flightTax;
        this.flightTotal = flightTotal;
        this.departureDate = departureDate;
        this.returnDate = null;
        this.roundTrip = false;
        this.departureFlightNumber = departureFlightNumber;
        this.returnFlightNumber = null;
        this.departurePartySize = departurePartySize;
        this.returnPartySize = 0;
        this.departureParty = departureParty;
        //this.returnParty = null;
    }

    /**
     *
     * Method used for creating a new reservation for a round trip. Information gathered from the API is stored here for later
     * retrieval by system (allows returning users to check their recent reservation).
     *
     * @param reservationID reservationID
     * @param departureDate departureDate
     * @param returnDate returnDate
     * @param departureFlightNumber departFlightNumber
     * @param returnFlightNumber returnFlightNumber
     * @param departurePartySize departurePartySize
     * @param returnPartySize returnPartySize
     * @param departureParty departureParty
     * @param returnParty returnParty
     *
     */
    // round trip
    public Reservation(int reservationID, Double flightPrice,  Double flightTax, Double flightTotal, String departureDate, String returnDate, String departureFlightNumber, String returnFlightNumber, int departurePartySize, int returnPartySize, ArrayList<Passenger> departureParty, ArrayList<Passenger> returnParty) {
        this.reservationID = reservationID;
        this.flightPrice = flightPrice;
        this.flightTax = flightTax;
        this.flightTotal = flightTotal;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.roundTrip = true;
        this.departureFlightNumber = departureFlightNumber;
        this.returnFlightNumber = returnFlightNumber;
        this.departurePartySize = departurePartySize;
        this.returnPartySize = returnPartySize;
        this.departureParty = departureParty;
        this.returnParty = returnParty;
    }

    /**
     *
     * Method for obtaining the Reservation ID.
     *
     * @return reservationID
     *
     */
    public int getReservationID() {
        return this.reservationID;
    }

    public Double getFlightPrice() {
        return this.flightPrice;
    }

    public Double getFlightTax() {
        return this.flightTax;
    }

    public Double getFlightTotal() {
        return this.flightTotal;
    }

    /**
     *
     * Method for obtaining Departure Date.
     *
     * @return departureDate
     *
     */
    public String getDepartureDate() {
        return this.departureDate;
    }

    /**
     *
     * Method for obtaining Return Date.
     *
     * @return returnDate
     *
     */
    public String getReturnDate() {
        return this.returnDate;
    }

    /**
     *
     * Method for obtaining Flight Type.
     *
     * @return True if flight type is round trip
     *
     */
    public boolean isRoundTrip() {
        return this.roundTrip;
    }

    /**
     *
     * Method for obtaining Flight Number.
     *
     * @return departureFlightNumber
     *
     */
    public String getDepartureFlightNumber() {
        return this.departureFlightNumber;
    }

    /**
     *
     * Method for obtaining Returning FLight Number.
     *
     * @return returnFlightNumber
     *
     */
    public String getReturnFlightNumber() {
        return this.returnFlightNumber;
    }

    /**
     *
     * Method for obtaining the size of the traveling party.
     *
     * @return departurePartySize
     *
     */
    public int getDeparturePartySize() {
        return this.departurePartySize;
    }

    /**
     *
     * Method for obtaining the size of the traveling party.
     *
     * @return returnPartySize
     *
     */
    public int getReturnPartySize() {
        return this.returnPartySize;
    }

    /**
     *
     * Array for getting an array containing the traveling passengers.
     *
     * @return departureParty
     *
     */
    public ArrayList<Passenger> getDepartureParty() {//work in this
        return departureParty;
    }

    /**
     *
     * Array for getting an array containing the traveling passengers.
     *
     * @return departureParty
     *
     */
    public ArrayList<Passenger> getReturnParty() {//work in this
        return returnParty;
    }

    /**
     *
     * Method for setting the Reservation ID.
     *
     * @param reservationID reservationID
     *
     *
     */
    //Setters
    public void setReservationID (int reservationID) {
        this.reservationID = reservationID;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public void setFlightTax(double flightTax) {
        this.flightTax = flightTax;
    }

    public void setFlightTotal(double flightTotal) {
        this.flightTotal = flightTotal;
    }

    /**
     *
     * Method for setting the Departure Date.
     *
     * @param departureDate departureDate
     *
     */
    public void setDepartureDate (String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     *
     * Method for setting the Return Date.
     *
     * @param returnDate returnDate
     *
     */
    public void setReturnDate (String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     *
     * Method for setting the Flight Type.
     *
     * @param roundTrip roundTrip
     *
     */
    public void setFlightType (boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    /**
     *
     * Method for setting the Departing Flight Number.
     *
     * @param departureFlightNumber departFlightNumber
     *
     */
    public void setDepartureFlightNumber(String departureFlightNumber) {
        this.departureFlightNumber = departureFlightNumber;
    }

    /**
     *
     * Method for setting Returning Flight Number.
     *
     * @param returnFlightNumber returnFlightNumber
     *
     */
    public void setReturnFlightNumber (String returnFlightNumber) {
        this.returnFlightNumber = returnFlightNumber;
    }

    /**
     *
     * Method for setting the size of the traveling party.
     *
     * @param departurePartySize departurePartySize
     *
     */
    public void setDeparturePartySize (int departurePartySize) {
        this.departurePartySize = departurePartySize;
    }

    /**
     *
     * Method for setting the size of the traveling party.
     *
     * @param returnPartySize returnPartySize
     *
     */
    public void setReturnPartySize (int returnPartySize) {
        this.returnPartySize = returnPartySize;
    }

    /**
     *
     * Array for setting an array containing the traveling passengers.
     *
     * @param departureParty departureParty
     */
    public void setDepartureParty (ArrayList departureParty) { //work in this
        this.departureParty = departureParty;
    }

    /**
     *
     * Array for setting an array containing the traveling passengers.
     *
     * @param returnParty returnParty
     */
    public void setReturnParty (ArrayList returnParty) { //work in this
        this.returnParty = returnParty;
    }

    /**
     *
     * Method for creating a new instance of Flight (for departing flight).
     * @param departureFlight
     */
    public void setDepartureFlight(Flight departureFlight) {
        this.departureFlight = departureFlight;
    }

    /**
     *
     * Method for creating a new instance of Flight (for returning flight).
     * @param returnFlight
     */
    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public Flight getDepartureFlight() {
        return departureFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }
}
