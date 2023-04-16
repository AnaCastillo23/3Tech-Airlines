package API;

import Class.Flight;
import Class.Airport;
import Helper.ScheduledDeparturesFilter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.ArrayList;


@RestController
public class FlightController {

    private final String airportAPI = "";
    private final String flightAPI = "https://aeroapi.flightaware.com/aeroapi/";
    private String API_KEY = "NMHhMozcCtn3FBViMiczGJBcA1mDCuA1";


    @GetMapping("/flight-data")
    public ArrayList<JSONObject> getScheduledDepartures(ScheduledDeparturesFilter filter) throws MalformedURLException {

        ApiClient apiClient = new ApiClient(flightAPI + "airports/" + filter.getAirportDepartureCode() + "/flights/scheduled_departures?type=Airline&start=" + filter.getDepartureDateAPI() + "&end=" + filter.getDepartureDateAPI() + "T23%3A59%3A59Z");

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
    /*
    Example:
    Depart from: LAX		Arrive to: SEA
    Los Angeles		  	Seattle
04/17/2023 11:11 am		04/17/2023 02:14 am
*/
    @GetMapping("/airport-list")
    public ArrayList<JSONObject> getAirportList() {
        return null;
    }


    @GetMapping("/operator-name")
    public ArrayList<JSONObject> getOperatorName() {
        return null;
    }
}