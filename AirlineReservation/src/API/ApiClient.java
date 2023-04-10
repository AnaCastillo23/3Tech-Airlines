package API;

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

public class ApiClient {

    private String urlString;

    public ApiClient() {
        this.urlString = "";
    }

    public ApiClient(String url) throws MalformedURLException {
        this.urlString = url;
    }

    public JSONArray getJSONArray(String dateQuery, String airportQuery, String API_KEY){
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString + "airports/" + airportQuery + "/flights/scheduled_departures?type=Airline&start=" + dateQuery + "&end=" + dateQuery + "T23%3A59%3A59Z"))
                    .headers("x-apikey", API_KEY)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            if (response.statusCode() == 200) {
                System.out.println("responseBody: " + response.body());
            }

            String dataString = "";
            dataString = response.body();

            JSONObject jsonObject = new JSONObject(dataString);
            JSONArray jsonArray = jsonObject.getJSONArray("scheduled_departures");

            return jsonArray;

            } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
