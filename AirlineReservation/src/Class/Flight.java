package Class;

import java.util.Date;

public class Flight {
    // helper classes
    protected Airport airport;
    protected Airline airline;

    private String departureDate;
    private Date departureDateFormatted;


    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
        this.departureDateFormatted = new Date();
    }

    public Date getDepartureDateFormatted() {
        return departureDateFormatted;
    }

    public String getDepartureDate() {
        return departureDate;
    }
}
