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
    private static Airport airport;
    private static Airline airline;

    private String flightID;
    private String departureDateAPI;
    private Date departureDate;
    private String arrivalDateAPI;
    private Date arrivalDate;
    private Time departureTime;
    private Time arrivalTime;
    private String departureLocation;
    private String arrivalLocation;

    /**
     *
     * Constructor that is empty, at the moment.
     *
     */
    public Flight() {
        this.flightID = null;
        this.departureDateAPI = null;
        this.departureDate = null;
        this.arrivalDateAPI = null;
        this.arrivalDate = null;
        this.departureTime = null;
        this.arrivalTime = null;
        this.departureLocation = null;
        this.arrivalLocation = null;
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
    public Flight(String flightID, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime, String departureLocation, String arrivalLocation) {
        this.flightID = flightID;

        //setDepartureDate(departureDate);
        this.departureDateAPI = getDepartureDateAPI();
        // delete this after bug fix
        this.departureDate = departureDate;

        //setArrivalDate(arrivalDate);
        this.arrivalDateAPI = getArrivalDateAPI();
        // delete this after bug fix
        this.arrivalDate = arrivalDate;

        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
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
     * Method for obtaining the Departure Date from the API, according to selected flight by user.
     *
     * @return departureDateAPI
     *
     */
    public String getDepartureDateAPI() {
        return departureDateAPI;
    }

    /**
     *
     * Method for obtaining the departure date from the flight itself. To be used later in project.
     *
     * @return departureDate
     *
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     *
     * Method for obtaining the Arrival Date from the API.
     *
     * @return arrivalDateAPI
     *
     */
    public String getArrivalDateAPI() {
        return arrivalDateAPI;
    }

    /**
     *
     * Method for obtaining the Arrival Date. To be used later in project.
     *
     * @return arrivalDate
     *
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     *
     * Method for obtaining the Departure Time. To be used later in project.
     *
     * @return departureTime
     *
     */
    public Time getDepartureTime() {
        return departureTime;
    }

    /**
     *
     * Method for obtaining the Arrival Time. To be used later in project.
     *
     * @return arrivalTime
     *
     */
    public Time getArrivalTime() {
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
    public static Airport getAirport() {
        return airport;
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
     * Method for setting the Departure Date from API. To be used later in project.
     *
     * @param departureDateAPI departureDateAPI
     *
     */
    public void setDepartureDateAPI(String departureDateAPI) {
        this.departureDateAPI = departureDateAPI;
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
        this.departureDateAPI = departureDate;
        this.departureDate = new Date();
    }

    /**
     *
     * Method for setting Arrival Date in the API. To be used later in project.
     *
     * @param arrivalDateAPI arrivalDateAPI
     *
     */
    public void setArrivalDateAPI(String arrivalDateAPI) {
        this.arrivalDateAPI = arrivalDateAPI;
    }

    /**
     *
     * Method for setting Arrival Date. To be used later in project.
     *
     * @param arrivalDate arrivalDate
     *
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     *
     * Method for setting Departure Time. To be used later in project.\
     *
     * @param departureTime departureTime
     *
     */
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    /**
     *
     * Method for setting Arrival Time. To be used later in project.
     *
     * @param arrivalTime arrivalTime
     *
     */
    public void setArrivalTime(Time arrivalTime) {
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
     * @param airportCode airportCode
     * @param airportName airportName
     * @param airportCity airportCity
     *
     */
    public static void setAirport(String airportCode, String airportName, String airportCity) {
        airport = new Airport(airportCode, airportName, airportCity);
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
