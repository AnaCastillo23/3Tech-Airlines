package API;

import Class.Flight;
import Class.Airport;
import Class.FlightSearchHelper;
import GUI.FlightSearchFrame;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;


@RestController
public class FlightController {

    private final String airportAPI = "";
    private final String flightAPI = "https://aeroapi.flightaware.com/aeroapi/";
    private String API_KEY = "NMHhMozcCtn3FBViMiczGJBcA1mDCuA1";


    @GetMapping("/flight-data")
    public ArrayList<JSONObject> getFlightData(FlightSearchHelper searchHelper) throws MalformedURLException {

        ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();

        ApiClient apiClient = new ApiClient(flightAPI);

        JSONArray jsonArray =  (apiClient.getJSONArray(searchHelper.getDepartureDateAPI(), searchHelper.getAirportCode(), API_KEY));

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject departuresObject = jsonArray.getJSONObject(i);
            System.out.println("departuresObject(" + i + ") = " + departuresObject);

            jsonObjectArrayList.add(departuresObject);
        }
        System.out.println(jsonObjectArrayList);

        return jsonObjectArrayList;
    }


    @GetMapping("/airport-list")
    public ArrayList<JSONObject> getAirportList(Flight flight, Airport airport) {
        return null;
    }
}