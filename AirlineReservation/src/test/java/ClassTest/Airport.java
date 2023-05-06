package ClassTest;

/**
 * The Airport class is used in conjunction with information recollected from
 * the API used in this project. All data concerning an airport will land in this class.
 * @since 03/27/2023
 * @author Carlos Figueroa (developed class) and Ana Emily Castillo Perez (added documentation comments).
 * <p>
 * <b>Explanation of important functions:</b> Class implements user's flight choice when API lists relevant flights
 * according to user's inputted flight characteristics.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>Airport(String airportCode, String airportName, String airportCity):</i> used to store airport properties.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class Airport {
    private String airportCode;
    private String airportName;

    //private String airportCountry;

    /**
     *
     * Constructor that is empty, at the moment.
     *
     */
    public Airport() {
        this.airportCode = null;
        this.airportName = null;
        //this.airportCountry = null;
    }

    /**
     *
     * Method for creating a new airport and set its properties. Information gathered from the API is stored here for later
     * retrieval by system (allows returning users to view to which airport their reservation was placed to).
     *
     * @param airportCode airportCode
     * @param airportName airportName
     *
     */
    public Airport(String airportCode, String airportName) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        //this.airportCountry = airportCountry;
    }

    /**
     *
     * Method for obtaining the Airport's Code.
     *
     * @return airportCode
     *
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     *
     * Method for obtaining the Airport's Name.
     *
     * @return airportName
     *
     */
    public String getAirportName() {
        return airportName;
    }


    //Ignore
    /*
    public String getAirportCountry() {
        return airportCountry;
    }
    */

    /**
     *
     * Method for setting the Airport's code.
     * @param airportCode airportCode
     *
     */
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    /**
     *
     * Method for setting the Airport's Name.
     *
     * @param airportName airportName
     *
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }


    //Ignore
    /*
    public void setAirportCountry(String airportCountry) {
        this.airportCountry = airportCountry;
    }
    */
}
