package Helper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookedFlightsReview {
    ArrayList<JSONObject> bookedFlights = new ArrayList<>();
    private static final Map<String, ArrayList<JSONObject>> tempBookedFlight = new HashMap<>();

    public void bookedFlightsToReview(ArrayList<JSONObject> bookedFlights) {
        this.bookedFlights = bookedFlights;

        // later add way do this under login account

        tempBookedFlight.put("temp", this.bookedFlights);
    }

    public ArrayList<JSONObject> getBookedFlightsReview(String username) {
        return tempBookedFlight.get("temp");
    }
}
