package Helper;

import Class.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FlightsToReview {
    private static final Map<String, ArrayList<Flight>> bookedFlightsList = new HashMap<>();
    //private static ArrayList<Flight> bookedFlights;

    public FlightsToReview() {
        //bookedFlights = new ArrayList<Flight>();
    }

    public FlightsToReview(ArrayList<Flight> bookedFlights) {
        //this.bookedFlights = bookedFlights;
        flightListToReviewFrame(bookedFlights);
    }

    public void flightListToReviewFrame(ArrayList<Flight> bookedFlights) {
        bookedFlightsList.put("temp", bookedFlights);
    }

    public ArrayList<Flight> getFlightsToDisplay(String username) {
        return bookedFlightsList.get("temp");
    }

    // merges API data GET scheduled-departures and GET operator into one object
    // not sure to add country...depends on size of ICAO list

}
