package Class;

public class Airport {
    private String airportCode;
    private String airportName;
    private String airportCity;
    //private String airportCountry;


    public Airport() {
        this.airportCode = null;
        this.airportName = null;
        this.airportCity = null;
        //this.airportCountry = null;
    }

    public Airport(String airportCode, String airportName, String airportCity) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.airportCity = airportCity;
        //this.airportCountry = airportCountry;
    }


    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCity() {
        return airportCity;
    }
    /*
    public String getAirportCountry() {
        return airportCountry;
    }
    */

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    /*
    public void setAirportCountry(String airportCountry) {
        this.airportCountry = airportCountry;
    }
    */
}
