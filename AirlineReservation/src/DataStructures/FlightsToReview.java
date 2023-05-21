package DataStructures;

import Class.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FlightsToReview {
    private static final Map<String, ArrayList<Flight>> bookedFlightsList = new HashMap<>();
    private static final Map<String, ArrayList<Double>> totalPrice = new HashMap<>();
    //private static ArrayList<Flight> bookedFlights;

    public FlightsToReview() {
        //bookedFlights = new ArrayList<Flight>();
    }

    public FlightsToReview(ArrayList<Flight> bookedFlights, Double departurePrice, Double returnPrice) {
        //this.bookedFlights = bookedFlights;
        flightListToReviewFrame(bookedFlights, departurePrice, returnPrice);
    }

    public void flightListToReviewFrame(ArrayList<Flight> bookedFlights, Double departurePrice, Double returnPrice) {
        ArrayList<Double> flightPrices = new ArrayList<>();
        flightPrices.add(departurePrice);
        if(returnPrice > 0) {
            flightPrices.add(returnPrice);
        }

        bookedFlightsList.put("temp", bookedFlights);
        totalPrice.put("temp", flightPrices);
    }

    public ArrayList<Flight> getFlightsToDisplay() {
        return bookedFlightsList.get("temp");
    }

    public ArrayList<Double> getTotalPrice() {
        return totalPrice.get("temp");
    }

    public void clearFlightToReview() {
        bookedFlightsList.clear();
        totalPrice.clear();
    }
}
