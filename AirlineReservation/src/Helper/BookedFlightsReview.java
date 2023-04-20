package Helper;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookedFlightsReview {
    BookedFlightBundle bundle;
    private static final Map<String, BookedFlightBundle> tempBookedFlight = new HashMap<>();


    public BookedFlightsReview() {

    }

    public BookedFlightsReview(ArrayList<Date> departDates, ArrayList<String[]> airlineOperator, ArrayList<JSONObject> bookedFlights) {
        bundle = new BookedFlightBundle(departDates, airlineOperator, bookedFlights);
        bookedFlightsToReview(bundle);
    }

    public void bookedFlightsToReview(BookedFlightBundle bookedFlights) {
        //this.bookedFlights = bookedFlights;

        // later add way do this under login account

        tempBookedFlight.put("temp", bookedFlights);
    }

    public BookedFlightBundle getBookedFlightsReview(String username) {
        return tempBookedFlight.get("temp");
    }

    // merges API data GET scheduled-departures and GET operator into one object
    // not sure to add country...depends on size of ICAO list
    public static class BookedFlightBundle {
        private ArrayList<String[]> airlineOperator;  //airlineOperator.add(new String[]{"operator code", "operator name"});
        private ArrayList<JSONObject> bookedFlights = new ArrayList<>();
        private ArrayList<Date> departDates = new ArrayList<>();


        public BookedFlightBundle() {
        }

        public BookedFlightBundle(ArrayList<Date> departDates, ArrayList<String[]> airlineOperator, ArrayList<JSONObject> bookedFlights) {
            this.departDates = departDates;
            this.airlineOperator = airlineOperator;
            //this.airlineOperator.add(new String[]{"operator code", "operator name"});
            this.bookedFlights = bookedFlights;
        }

        public ArrayList<String[]> getAirlineOperator() {
            return airlineOperator;
        }

        public ArrayList<JSONObject> getBookedFlights() {
            return bookedFlights;
        }

        public ArrayList<Date> getDepartDates() {
            return departDates;
        }

        public void setAirlineOperator(ArrayList<String[]> airlineOperator) {
            this.airlineOperator = airlineOperator;
        }

        public void setBookedFlights(ArrayList<JSONObject> bookedFlights) {
            this.bookedFlights = bookedFlights;
        }

        public void setDepartDates(ArrayList<Date> departDates) {
            this.departDates = departDates;
        }
    }
}
