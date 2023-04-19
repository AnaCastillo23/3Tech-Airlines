package Class;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

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

    private int reservationID;
    private Date departureDate;
    private Date returnDate;
    private boolean roundTrip;
    private String departFlightNumber;
    private String returnFlightNumber;
    private int partySize;
    public static ArrayList<Passenger> party = new ArrayList<>();


    /**
     *
     * Constructor that is empty, at the moment.
     *
     */
    public Reservation() {
        this.reservationID = 0;
        this.departureDate = null;
        this.returnDate = null;
        this.roundTrip = false;
        this.departFlightNumber = null;
        this.returnFlightNumber = null;
        this.partySize = 0;
    }

    /**
     *
     * Method used for creating a new reservation for a one-way trip. Information gathered from the API is stored here for later
     * retrieval by system (allows returning users to check their recent reservation).
     *
     * @param reservationID reservationID
     * @param departureDate departureDate
     * @param departFlightNumber departFlightNumber
     * @param returnFlightNumber returnFlightNumber
     * @param partySize partySize
     * @param party party
     *
     */
    // one way trip
    public Reservation(int reservationID, Date departureDate, String departFlightNumber, String returnFlightNumber, int partySize, ArrayList<Passenger> party) {
        this.reservationID = reservationID;
        this.departureDate = departureDate;
        this.returnDate = null;
        this.roundTrip = false;
        this.departFlightNumber = departFlightNumber;
        this.returnFlightNumber = null;
        this.partySize = partySize;
        this.party = party;
    }

    /**
     *
     * Method used for creating a new reservation for a round trip. Information gathered from the API is stored here for later
     * retrieval by system (allows returning users to check their recent reservation).
     *
     * @param reservationID reservationID
     * @param departureDate departureDate
     * @param returnDate returnDate
     * @param departFlightNumber departFlightNumber
     * @param returnFlightNumber returnFlightNumber
     * @param partySize partySize
     * @param party party
     *
     */
    // round trip
    public Reservation(int reservationID, Date departureDate, Date returnDate, String departFlightNumber, String returnFlightNumber, int partySize, ArrayList<Passenger> party) {
        this.reservationID = reservationID;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.roundTrip = true;
        this.departFlightNumber = departFlightNumber;
        this.returnFlightNumber = returnFlightNumber;
        this.partySize = partySize;
        this.party = party;
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

    /**
     *
     * Method for obtaining Departure Date.
     *
     * @return departureDate
     *
     */
    public Date getDepartureDate() {
        return this.departureDate;
    }

    /**
     *
     * Method for obtaining Return Date.
     *
     * @return returnDate
     *
     */
    public Date getReturnDate() {
        return this.returnDate;
    }

    /**
     *
     * Method for obtaining Flight Type.
     *
     * @return True if flight type is round trip
     *
     */
    public boolean getFlightType() {
        return this.roundTrip;
    }

    /**
     *
     * Method for obtaining Flight Number.
     *
     * @return departureFlightNumber
     *
     */
    public String getDepartFlightNumber() {
        return this.departFlightNumber;
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
     * @return partySize
     *
     */
    public int getPartySize() {
        return this.partySize;
    }

    /**
     *
     * Array for getting an array containing the traveling passengers.
     *
     * @return getParty
     *
     */
    public ArrayList<Passenger> getParty() {//work in this
        return this.getParty();
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

    /**
     *
     * Method for setting the Departure Date.
     *
     * @param departureDate departureDate
     *
     */
    public void setDepartureDate (Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     *
     * Method for setting the Return Date.
     *
     * @param returnDate returnDate
     *
     */
    public void setReturnDate (Date returnDate) {
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
     * @param departFlightNumber departFlightNumber
     *
     */
    public void setDepartFlightNumber (String departFlightNumber) {
        this.departFlightNumber = departFlightNumber;
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
     * @param partySize partySize
     *
     */
    public void setPartySize (int partySize) {
        this.partySize = partySize;
    }

    /**
     *
     * Array for setting an array containing the traveling passengers.
     *
     * @param party party
     */
    public void setParty (ArrayList party) { //work in this
        this.party = party;
    }

    /**
     *
     * Method for creating a new instance of Flight (for departing flight).
     *
     * @param flightID flightID
     * @param departureDate departureDate
     * @param arrivalDate arrivalDate
     * @param departureTime departureTime
     * @param arrivalTime arrivalTime
     * @param departureLocation departureLocation
     * @param arrivalLocation arrivalLocation
     */
    public void setDepartureFlight(String flightID, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime, String departureLocation, String arrivalLocation) {
        departureFlight = new Flight(flightID, departureDate, arrivalDate, departureTime, arrivalTime, departureLocation, arrivalLocation);
    }

    /**
     *
     * Method for creating a new instance of Flight (for returning flight in case it is a round trip).
     *
     * @param flightID flightID
     * @param departureDate departureDate
     * @param arrivalDate arrivalDate
     * @param departureTime departureTime
     * @param arrivalTime arrivalTime
     * @param departureLocation departureLocation
     * @param arrivalLocation arrivalLocation
     *
     */
    // roundTrip
    public void setReturnFlight(String flightID, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime, String departureLocation, String arrivalLocation) {
        returnFlight = new Flight(flightID, departureDate, arrivalDate, departureTime, arrivalTime, departureLocation, arrivalLocation);
    }

    //IGNORE FOllOWING
    // NOT SURE IF THIS IS NEEDED
    /*
    public Flight getDepartureFlight() {
        return getDepartureFlight();
    }

    public Flight getReturnFlight() {
        return getReturnFlight();
    }*/
}
