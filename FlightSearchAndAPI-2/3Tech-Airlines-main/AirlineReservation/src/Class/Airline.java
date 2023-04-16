package Class;

public class Airline {
    private String airlineID;
    private String airlineName;


    public Airline() {
        this.airlineID = null;
        this.airlineName = null;
    }

    public Airline(String airlineID, String airlineName) {
        this.airlineID = airlineID;
        this.airlineName = airlineName;
    }


    public String getAirlineID() {
        return airlineID;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
}
