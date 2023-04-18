package API;

import Helper.ScheduledDeparturesFilter;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * @since 04/17/2023
 * @author Carlos Figueroa
 * <p>
 * <b>Description of the class/module:</b>
 * <p>
 * <b>Explanation of important functions:</b>
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 *  * <li><i></i></li>
 *  * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class FlightModel {
    // 1. Schedule Departures
    // 2. Airport List (JScroll)
    // 3. Airline operator name

    public ArrayList<JSONObject> getScheduledDepartuesFiltered(ScheduledDeparturesFilter filter) throws MalformedURLException {
        FlightController controller = new FlightController();
        ArrayList<JSONObject> scheduledDeparturesFiltered = new ArrayList<>();

        ArrayList<JSONObject> scheduledDepartures = controller.getScheduledDepartures(filter);

        for (int i = 0; i < scheduledDepartures.size(); i++) {
            JSONObject jsonObj1 = (JSONObject) scheduledDepartures.get(i);
            System.out.println("jsonObj(" + i + ") : " + jsonObj1.get("destination"));
            Object obj = jsonObj1.get("destination");

            JSONObject jsonObj2 = new JSONObject(String.valueOf(obj));
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

        return scheduledDeparturesFiltered;
    }
}
