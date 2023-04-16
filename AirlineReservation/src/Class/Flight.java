package Class;

import java.sql.Time;
import java.util.Date;

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


    public String getFlightID() {
        return flightID;
    }

    public String getDepartureDateAPI() {
        return departureDateAPI;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDateAPI() {
        return arrivalDateAPI;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public static Airport getAirport() {
        return airport;
    }

    public static Airline getAirline() {
        return airline;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setDepartureDateAPI(String departureDateAPI) {
        this.departureDateAPI = departureDateAPI;
    }

    // make this take (Date departure) -> setArrivalDateAPI(departure)
    public void setDepartureDate(String departureDate) {
        this.departureDateAPI = departureDate;
        this.departureDate = new Date();
    }

    public void setArrivalDateAPI(String arrivalDateAPI) {
        this.arrivalDateAPI = arrivalDateAPI;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public static void setAirport(String airportCode, String airportName, String airportCity) {
        airport = new Airport(airportCode, airportName, airportCity);
    }

    public static void setAirline(String airlineOperator) {
        // API call to "GET /operators/{id}" to get airline name using id = airlineID
        // String airlineName = API call

        airline = new Airline(airlineOperator, null);
    }


}
