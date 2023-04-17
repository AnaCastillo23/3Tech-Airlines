package Helper;

public class ScheduledDeparturesFilter {
    private String airportDepartureCode;
    private String airportArrivalCode;
    private String departureDate;
    private String departureDateAPI;


    public ScheduledDeparturesFilter() {
    }

    public ScheduledDeparturesFilter(String airportDepartureCode, String airportArrivalCode, String departureDate) {
        this.airportDepartureCode = airportDepartureCode;
        this.airportArrivalCode = airportArrivalCode;

        this.departureDate = departureDate;
        setDepartureDateAPI(getDepartureDate());
        this.departureDateAPI = getDepartureDateAPI();
    }


    public String getAirportDepartureCode() {
        return airportDepartureCode;
    }

    public String getAirportArrivalCode() {
        return airportArrivalCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureDateAPI() {
        return departureDateAPI;
    }

    public void setAirportDepartureCode(String airportDepartureCode) {
        this.airportDepartureCode = airportDepartureCode;
    }

    public void setAirportArrivalCode(String airportDepartureCode) {
        this.airportArrivalCode = airportArrivalCode;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureDateAPI(String departureDate) {
        String departureDateAPIStr;

        departureDateAPIStr = departureDate.substring(6) + "-" + departureDate.substring(0,2) + "-" + departureDate.substring(3,5);
        System.out.println(departureDateAPIStr);
        this.departureDateAPI = departureDateAPIStr;
    }
}
