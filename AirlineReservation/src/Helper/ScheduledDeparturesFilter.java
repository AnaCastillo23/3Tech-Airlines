package Helper;

/**
 * Creates object that serves as parameters that filters API call dataset.  Essential in
 * GUI FlightSearchFrame using "Depart From", "Arrive to", and "Date" forms.  This object is used for FlightModel class.
 * <p>
 *
 * @since 04/17/2023
 * @author Carlos Figueroa
 * <p>
 *
 * <b>Explanation of important functions:</b>  Constructor creates parameters that will filter large dataset from Api call.
 * SetDepartureDateAPI(String departureDate) converts date given by user in FlightSearchFrame from Date format to a String format
 * that is used as a parameter in the api call.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>ScheduledDeparturesFilter(String airportDepartureCode, String airportArrivalCode, String departureDate)</i></i></li>
 * <li><i>getAirportDepartureCode()</i></li>
 * <li><i>getAirportArrivalCode()</i></li>
 * <li><i>getDepartureDate()</i></li>
 * <li><i>getDepartureDateAPI()</i></li>
 * <li><i>setAirportDepartureCode(String airportDepartureCode)</i></li>
 * <li><i>setAirportArrivalCode(String airportDepartureCode)</i></li>
 * <li><i>setDepartureDate(String departureDate)</i></li>
 * <li><i>setDepartureDateAPI(String departureDate)</i></li>
 * </ul>
 * <p>
 *
 * Any algorithms used?
 *
 */
public class ScheduledDeparturesFilter {
    private String airportDepartureCode;
    private String airportArrivalCode;
    private String departureDate;
    private String departureDateAPI;

    public ScheduledDeparturesFilter() {
    }

    /**
     *
     * Constructor Method
     *
     * @param airportDepartureCode airportDepartureCode
     * @param airportArrivalCode airportArrivalCode
     * @param departureDate departureDate
     *
     */
    public ScheduledDeparturesFilter(String airportDepartureCode, String airportArrivalCode, String departureDate) {
        this.airportDepartureCode = airportDepartureCode;
        this.airportArrivalCode = airportArrivalCode;

        this.departureDate = departureDate;
        setDepartureDateAPI(getDepartureDate());
        this.departureDateAPI = getDepartureDateAPI();
    }


    /**
     *
     * Method for retrieving an airport's departure code
     *
     * @return airportDepartureCode
     *
     */
    public String getAirportDepartureCode() {
        return airportDepartureCode;
    }

    /**
     *
     * Method for retrieving an airport's arrival code
     *
     * @return getAirportArrivalCode
     */
    public String getAirportArrivalCode() {
        return airportArrivalCode;
    }

    /**
     *
     * Method for retrieving user's inputted departure date
     *
     * @return getDepartureDate
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     *
     * Method for retrieving the user's departure date in API format
     *
     * @return departureDateAPI
     */
    public String getDepartureDateAPI() {
        return departureDateAPI;
    }


    /**
     *
     * Method for setting airport departure code for api parameter
     *
     */
    public void setAirportDepartureCode(String airportDepartureCode) {
        this.airportDepartureCode = airportDepartureCode;
    }

    /**
     *
     * Method for setting airport arrival code for api parameter
     *
     */
    public void setAirportArrivalCode(String airportDepartureCode) {
        this.airportArrivalCode = airportArrivalCode;
    }

    /**
     *
     * Method for setting departure date for api parameter
     *
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     *
     * Method converts date given by user in FlightSearchFrame from Date format to a String format that is used as a parameter
     * in the api call.  When making api request for returning flight, simply replace parameter departureDate to returnDate.
     *
     * @param departureDate departureDate
     *
     */
    public void setDepartureDateAPI(String departureDate) {
        String departureDateAPIStr;

        departureDateAPIStr = departureDate.substring(6) + "-" + departureDate.substring(0,2) + "-" + departureDate.substring(3,5);
        System.out.println(departureDateAPIStr);
        this.departureDateAPI = departureDateAPIStr;
    }
}
