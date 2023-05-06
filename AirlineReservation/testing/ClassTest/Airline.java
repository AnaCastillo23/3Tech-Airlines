package ClassTest;

/**
 * The Airline class is used in conjunction with information recollected from
 * the API used in this project. All data concerning an Airline will land in this class.
 * @since 04/18/2023
 * @author Carlos Figueroa (developed class) and Ana Emily Castillo Perez (added documentation comments).
 * <p>
 * <b>Explanation of important functions:</b> Class implements user's flight choice when API lists relevant flights
 * according to user's inputted flight characteristics.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>public Airline(String airlineID, String airlineName):</i> used to store airline propeerties.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class Airline {
    private String airlineID;
    private String airlineName;

    /**
     *
     * Constructor that is empty, at the moment.
     *
     */
    public Airline() {
        this.airlineID = null;
        this.airlineName = null;
    }

    /**
     *
     * Method for setting a new Airline and set its properties. Information gathered from the API is stored here for later
     * retrieval by system (allows returning users to view information about a flight user booked).
     *
     * @param airlineID airlineID
     * @param airlineName airlineName
     *
     */
    public Airline(String airlineID, String airlineName) {
        this.airlineID = airlineID;
        this.airlineName = airlineName;
    }

    /**
     *
     * Method for obtaining an Airline's ID. To be used later in project.
     *
     * @return airlineID
     *
     */
    public String getAirlineID() {
        return airlineID;
    }

    /**
     *
     * Method for obtaining an Airline's name. To be used later in project.
     *
     * @return airlineName
     *
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     *
     * Method for setting Airline's ID. To be used later in project.
     * @param airlineID airlineID
     *
     */
    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    /**
     *
     * Method for setting an Airline's name for later retrieval by returning user's. To be used later in project.
     *
     * @param airlineName airlineName
     *
     */
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
}
