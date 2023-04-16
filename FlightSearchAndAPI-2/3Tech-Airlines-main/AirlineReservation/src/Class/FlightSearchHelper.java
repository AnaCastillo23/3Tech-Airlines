package Class;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightSearchHelper {
    private String airportCode;
    private String departureDate;
    private String departureDateAPI;


    public FlightSearchHelper() {
    }

    public FlightSearchHelper(String airportCode, String departureDate) {
        this.airportCode = airportCode;
        this.departureDate = departureDate;

        setDepartureDateAPI(getDepartureDate());
        this.departureDateAPI = getDepartureDateAPI();
    }


    public String getAirportCode() {
        return airportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureDateAPI() {
        return departureDateAPI;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureDateAPI(String departureDate) {
        String departureDateAPIStr;

        departureDateAPIStr = departureDate.substring(6) + "-" + departureDate.substring(3,5) + "-" + departureDate.substring(0,2);
        System.out.println(departureDateAPIStr);
        this.departureDateAPI = departureDateAPIStr;
    }
}
