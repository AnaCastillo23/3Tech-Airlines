package APITest;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * <p>
 * The model for flight data and blueprint that molds incoming API data.  Filters
 * out unnecessary data from API.
 * <p>
 * @since 04/04/2023
 * @author Carlos Figueroa
 * <b>Explanation of important functions:</b>
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>getScheduledDepartuesFiltered(ScheduledDeparturesFilter filter)</i></li>
 * <li><i>getAirportData()</i></li>
 * <li><i>getAirlineOperatorInfo(String operatorCode)</i></li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class FlightModel {
    APITest.FlightController controller;
    // 1. Schedule Departures
    // 2. Airport List (JScroll)
    // 3. Airline operator name

    /**
     * Method calls getScheduledDepartures(filter) in FlightController.java to get a filtered Array of scheduled departing
     * flight data.  This method then further narrows it down to flights departing to a specific airport determined by user
     * in the GUI.  getAirportList() makes a call to getAirportICAOList() in FlightController.java
     * @param filter query parameters for api call
     * @return ArrayList&lt;JSONObject>
     */
    public ArrayList<JSONObject> getScheduledDepartuesFiltered(ScheduledDeparturesFilter filter) throws MalformedURLException {
        ArrayList<JSONObject> scheduledDeparturesFiltered = new ArrayList<>();

        try {
            controller = new APITest.FlightController();
            ArrayList<JSONObject> scheduledDepartures = controller.getScheduledDepartures(filter);

            for (int i = 0; i < scheduledDepartures.size(); i++) {
                JSONObject jsonObj1 = scheduledDepartures.get(i);
                System.out.println("jsonObj(" + i + ") : " + jsonObj1.get("destination"));
                Object obj = jsonObj1.get("destination");

                if(obj != null) {
                    JSONObject jsonObj2 = new JSONObject(String.valueOf(obj));  // this is where error is happening

                    System.out.println("code(" + i + ") : " + jsonObj2.get("code"));
                    String code = (String) jsonObj2.get("code");

                    System.out.println(filter.getAirportArrivalCode());
                    System.out.println(code);
                    if (code.equals(String.valueOf(filter.getAirportArrivalCode()))) {
                        System.out.println(true);
                        scheduledDeparturesFiltered.add(jsonObj1);
                    } else {
                        System.out.println(false);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("API Error");
        }

        return scheduledDeparturesFiltered;
    }

    /**
     *
     * Method gets list of data from all airports such as name, city, state, and iata code.
     * @return ArrayList&lt;JSONObject>
     */
    public ArrayList<JSONObject> getAirportData() throws MalformedURLException {
        ArrayList<JSONObject> airportDataList = new ArrayList<>();

        controller = new APITest.FlightController();
        airportDataList = controller.getAirportICAOList();
        return airportDataList;
    }

    /**
     * With the help of getScheduledDepartuesFiltered(filter), this method will get a list of operator information
     * from all displayed flights available for booking. Method will be called multiple times in FlightSearchFrame gui depending
     * on how many flights are displayed for booking.
     * @param operatorCode airline operator code such as DEL for Delta or UAL for United Airlines
     * @return ArrayList&lt;JSONObject>
     */
    public ArrayList<JSONObject> getAirlineOperatorInfo(String operatorCode) throws MalformedURLException {
        ArrayList<JSONObject> airlineOperatorData = new ArrayList<>();
        // name, phone?, url?
        controller = new FlightController();
        airlineOperatorData = controller.getOperatorName(operatorCode);

        return airlineOperatorData;
    }
}
