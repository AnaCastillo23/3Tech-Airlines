package APITest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONString;

/**
 * The ApiClient class is used to send http requests to AeroAPI for real-time
 * flight data including scheduled departures, airport lists, and airline operator names in JSON format.  Type of http
 * request depends on url given by user.
 * <p>
 * @since 04/04/2023
 * @author Carlos Figueroa
 * <b>Explanation of important functions:</b>  URL for http request is given when the user creates an ApiClient object
 * from the FlightController class.  URL depends on request type from user.  URL consists of "https://aeroapi.flightaware.com/aeroapi" appended
 * with GET paths including:
 * <ul>
 * <li><i>Scheduled Departures:</i>   "/airports/{id}/flights/scheduled_departures"</li>
 * <li><i>Airport Lists:</i>   "/airports"</li>
 * <li><i>Airline Operator:</i>   "/operators/{id}"</li>
 * </ul>
 * Once an ApiClient object has been created along with its designated URL, the user calls getJSONObject(API_KEY) and
 * passes an AeroAPI API_KEY from the FlightController class.  The API_KEY is required to make a http request to the
 * AeroAPI website to return real-time flight data.  The data is returned to the FlightController class as a JSONObject
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>getJSONObject(String API_KEY):</i> used to get real-time flight data in the form of a JSONObject</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class ApiClient {

    private final String urlString;

    /**
     *
     * Constructor Method that takes url String for API request
     * @param url url address used for HttpRequest.
     *
     */
    public ApiClient(String url) {
        this.urlString = url;
    }

    /**
     *
     * Method that makes HttpRequest to AeroAPI for real-time flight data using API_KEY and url.  Returns data as JSON Object.
     * @param API_KEY code used to identify AeroAPI user.  Required for API request.
     * @return JSONObject
     */
    public JSONObject getJSONObject(String API_KEY) {
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .headers("x-apikey", API_KEY)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            if (response.statusCode() == 200) {
                System.out.println("responseBody: " + response.body());
            }

            String dataString = "";
            dataString = response.body();

            return new JSONObject(dataString);

            } catch (InterruptedException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
