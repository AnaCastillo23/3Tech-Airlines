package Class;

import java.sql.Time;
import java.util.Date;

/**
 * The Flight class is used in conjunction with information recollected from
 * the API used in this project. All data concerning a flights will land in this class.
 * @since 04/18/2023
 * @author Carlos Figueroa (developed class) and Ana Emily Castillo Perez (added documentation comments).
 * <p>
 * <b>Explanation of important functions:</b> Class implements user's flight choice when API lists relevant flights
 * according to user's inputted flight characteristics.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>Flight(String flightID, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime, String departureLocation, String arrivalLocation):</i> used to store flight properties.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class Flight {
    // helper classes
    private static Airport departureAirport;
    private static Airport arrivalAirport;
    private static Airline airline;

    private String flightID;
    private String departureDate;
    private String arrivalDate;
    private String departureTime;
    private String arrivalTime;
    private String departureLocation;
    private String arrivalLocation;

    /**
     *
     * Constructor that is empty, at the moment.
     *
     */
    public Flight() {
        this.flightID = null;
        this.departureDate = null;
        this.arrivalDate = null;
        this.departureTime = null;
        this.arrivalTime = null;
        this.departureLocation = null;
        this.arrivalLocation = null;
        departureAirport = new Airport();
        arrivalAirport = new Airport();
        airline = new Airline();
    }

    /**
     *
     * Method for creating a new flight and set its properties. Information gathered from the API is stored here for later
     * retrieval by system (allows returning users to view information about a flight user booked).
     *
     * @param flightID flightID
     * @param departureDate departureDate
     * @param arrivalDate arrivalDate
     * @param departureTime departureTime
     * @param arrivalTime arrivalTime
     * @param departureLocation departureLocation
     * @param arrivalLocation arrivalLocation
     */
    public Flight(String flightID, String departureDate, String arrivalDate, String departureTime, String arrivalTime, String departureLocation, String arrivalLocation) {
        this.flightID = flightID;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        departureAirport = new Airport();
        arrivalAirport = new Airport();
        airline = new Airline();
    }

    /**
     *
     * Method for obtaining the Flight's ID.
     *
     * @return flightID
     *
     */
    public String getFlightID() {
        return flightID;
    }

    /**
     *
     * Method for obtaining the departure date from the flight itself. To be used later in project.
     *
     * @return departureDate
     *
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     *
     * Method for obtaining the Arrival Date. To be used later in project.
     *
     * @return arrivalDate
     *
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     *
     * Method for obtaining the Departure Time. To be used later in project.
     *
     * @return departureTime
     *
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     *
     * Method for obtaining the Arrival Time. To be used later in project.
     *
     * @return arrivalTime
     *
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     *
     * Method for obtaining the Departure Location. To be used later in project.
     *
     * @return departureLocation
     *
     */
    public String getDepartureLocation() {
        return departureLocation;
    }

    /**
     *
     * Method for obtaining the Arrival Location. To be used later in project.
     *
     * @return arrivalLocation
     *
     */
    public String getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     *
     * Method for returning the stored Airport's Name. To be used later in project.
     *
     * @return airport
     *
     */
    public static Airport getDepartureAirport() {
        return departureAirport;
    }

    /**
     *
     * Method for returning the stored Airport's Name. To be used later in project.
     *
     * @return airport
     *
     */
    public static Airport getArrivalAirport() {
        return arrivalAirport;
    }


    /**
     *
     * Method for returning the stored Airline data. To be used later in project.
     * @return airline
     *
     */
    public static Airline getAirline() {
        return airline;
    }

    /**
     *
     * Method for setting the Flight ID. To be used later in project.
     *
     * @param flightID flightID
     *
     */
    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    /**
     *
     * Method for setting a flight's Departure Date and pass it onto the API. To be used later in project.
     *
     * @param departureDate departureDate
     *
     */
    // make this take (Date departure) -> setArrivalDateAPI(departure)
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     *
     * Method for setting Arrival Date. To be used later in project.
     *
     * @param arrivalDate arrivalDate
     *
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     *
     * Method for setting Departure Time. To be used later in project.\
     *
     * @param departureTime departureTime
     *
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     *
     * Method for setting Arrival Time. To be used later in project.
     *
     * @param arrivalTime arrivalTime
     *
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     *
     * Method for setting Departure Location. To be used later in project.
     * @param departureLocation departureLocation
     *
     */
    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    /**
     *
     * Method for setting Arrival Location. To be used later in project.
     *
     * @param arrivalLocation arrivalLocation
     *
     */
    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    /**
     *
     * Method for setting Airport. To be used later in project.
     *
     * @param departureAirport
     *
     */
    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     *
     * Method for setting Airport. To be used later in project.
     *
     * @param arrivalAirport arrivalAirport
     *
     */
    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    /**
     *
     * Method for setting Airline according to data gathered from API. To be used later in project.
     *
     * @param airlineName airlineName
     *
     */
    public static void setAirline(String airlineName) {

        airline = new Airline(airlineName, null);
    }
}
