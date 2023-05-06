package DataStructuresTest;

import ClassTest.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FlightsToReview {
    private static final Map<String, ArrayList<Flight>> bookedFlightsList = new HashMap<>();
    private static final Map<String, Double> totalPrice = new HashMap<>();
    //private static ArrayList<Flight> bookedFlights;

    public FlightsToReview() {
        //bookedFlights = new ArrayList<Flight>();
    }

    public FlightsToReview(ArrayList<Flight> bookedFlights, Double flightPrice) {
        //this.bookedFlights = bookedFlights;
        flightListToReviewFrame(bookedFlights, flightPrice);
    }

    public void flightListToReviewFrame(ArrayList<Flight> bookedFlights, Double flightPrice) {
        bookedFlightsList.put("temp", bookedFlights);
        totalPrice.put("temp", flightPrice);
    }

    public ArrayList<Flight> getFlightsToDisplay() {
        return bookedFlightsList.get("temp");
    }

    public Double getTotalPrice() {
        return totalPrice.get("temp");
    }

    public void clearFlightToReview() {
        bookedFlightsList.clear();
        totalPrice.clear();
    }
}
