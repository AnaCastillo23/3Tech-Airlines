/**
 *
 * Module name\Class name: ApiClient (class)
 * @since 04/17/2023
 * @author Carlos Figueroa (built structure of class)
 *
 * Description of the class/module:
 *
 * Explanation of important functions:
 *
 * Important data structure in class/important methods in class:
 *
 *
 *
 * Any algorithms used? Not at the moment.
 *
 */

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

    public JSONObject getJSONObject(String API_KEY){
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
