import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import org.json.JSONObject;

/**
 * WeatherFetcher.java
 *
 * Fetches current weather data from Open-Meteo API and displays it.
 */
public class WeatherFetcher {

    public static void main(String[] args) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=40.71&longitude=-74.01&current_weather=true";

        try {
            // Create HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Build GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            // Send request and get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON response
            JSONObject json = new JSONObject(response.body());

            // Extract "current_weather" object
            JSONObject currentWeather = json.getJSONObject("current_weather");

            // Display structured data
            System.out.println("Weather Data for Latitude 40.71, Longitude -74.01 (New York City):");
            System.out.println("-----------------------------------------------------------");
            System.out.println("Temperature: " + currentWeather.getDouble("temperature") + " °C");
            System.out.println("Wind Speed: " + currentWeather.getDouble("windspeed") + " km/h");
            System.out.println("Wind Direction: " + currentWeather.getDouble("winddirection") + "°");
            System.out.println("Weather Code: " + currentWeather.getInt("weathercode"));
            System.out.println("Time: " + currentWeather.getString("time"));

        } catch (Exception e) {
            System.err.println("Error fetching or parsing weather data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
