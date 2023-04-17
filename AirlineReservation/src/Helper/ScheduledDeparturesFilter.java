/**
 *
 * Module name\Class name: ScheduledDepartureFilter (class)
 * @since
 * @author Carlos Figueroa
 *
 *
 * Description of the class/module:
 *
 * Explanation of important functions:
 *
 * Important data structure in class/important methods in class:
 *
 *
 * Any algorithms used?
 *
 */

package Helper;

/**
 *
 * Class is for...
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
     * Method for referencing the current object.
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
     * Method for retrieving the user's departure date from API (?)
     *
     * @return departureDateAPI
     */
    public String getDepartureDateAPI() {
        return departureDateAPI;
    }

    //These methods do not get used! Delete them?????

    public void setAirportDepartureCode(String airportDepartureCode) {
        this.airportDepartureCode = airportDepartureCode;
    }

    public void setAirportArrivalCode(String airportDepartureCode) {
        this.airportArrivalCode = airportArrivalCode;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     *
     * Method for setting the departure date and connecting user's dates input (and their format) to work with API.
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
