package API;

import Class.ScheduledDeparturesFilter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * The FlightController is used to make specific API calls to the ApiClient class
 * Type of API calls include scheduled departures, airport lists, and airline operators.  This data sent from the ApiClient
 * is then returned to the original calling method in the form of ArrayList&lt;JSONObject>
 * <p>
 * @since 04/04/2023
 * @author Carlos Figueroa
 * <b>Explanation of important functions:</b>  There are three methods used for different api requests.  getScheduledDepartures(ScheduledDeparturesFilter filter)
 * takes an entire list of arriving and departing flights from a certain airport and narrows it down to all departing flights.
 * getAirportICAOList() returns an entire list of known airports.  Finally, given an airline operator code,
 * getOperatorName(String operatorCode) returns the String name of the airline.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>getScheduledDepartures(ScheduledDeparturesFilter filter)</i></li>
 * <li><i>getAirportICAOList()</i></li>
 * <li><i>getOperatorName(String operatorCode)</i></li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
@RestController
public class FlightController {

    private final String aeroApiUrl = "https://aeroapi.flightaware.com/aeroapi/";
    private final String airportAPI = "https://aeroapi.flightaware.com/aeroapi/airports";
    private String API_KEY = "NMHhMozcCtn3FBViMiczGJBcA1mDCuA1";

    /**
     *
     * Method that makes an API call to ApiClient for all departing and arriving flights at a specific airport.
     * Uses a filter to narrow list to scheduled_departures from departing airport to arriving airport.  Returns ArrayList &lt;JSONObject>
     * @param filter query parameters for api call
     * @return ArrayList&lt;JSONObject>
     */
    @GetMapping("/flight-data")
    public ArrayList<JSONObject> getScheduledDepartures(ScheduledDeparturesFilter filter) throws MalformedURLException {

        // one page
        ApiClient apiClient = new ApiClient(aeroApiUrl + "airports/" + filter.getAirportDepartureCode() + "/flights/scheduled_departures?type=Airline&start=" + filter.getDepartureDateAPI() + "&end=" + filter.getDepartureDateAPI() + "T23%3A59%3A59Z");

        // more than one page
        //ApiClient apiClient = new ApiClient(aeroApiUrl + "airports/" + filter.getAirportDepartureCode() + "/flights/scheduled_departures?type=Airline&start=" + filter.getDepartureDateAPI() + "&end=" + filter.getDepartureDateAPI() + "T23%3A59%3A59Z&max_pages=3");

        ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();

        JSONObject jsonObject =  apiClient.getJSONObject(API_KEY);
        JSONArray jsonArray = jsonObject.getJSONArray("scheduled_departures");

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject departuresObject = jsonArray.getJSONObject(i);
            System.out.println("departuresObject(" + i + ") = " + departuresObject);

            jsonObjectArrayList.add(departuresObject);
        }
        System.out.println(jsonObjectArrayList);

        return jsonObjectArrayList;
    }

    /**
     *
     * Method that makes API call to ApiClient for all airport ICAO codes and stores in ArrayList.
     * @return ArrayList&lt;JSONObject>
     */
    @GetMapping("/airport-list")
    public ArrayList<JSONObject> getAirportICAOList() {
        return null;
    }

    /**
     * Method that makes API call to ApiClient for airline information given an operator code and stores in ArrayList.
     * Information such as airline name, airline headquarters, airline phone number, etc.  Method will be called multiple
     * times from FlightSearchFrame gui and make multiple API calls depending on how many flights are displayed for booking.
     * @param operatorCode airline operator code such as DEL for Delta or UAL for United Airlines
     * @return ArrayList&lt;JSONObject>
     */
    @GetMapping("/operator-name")
    public ArrayList<JSONObject> getOperatorName(String operatorCode) {
        return null;
    }
}