import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class WeatherRequest {
    private static final String URL_API = "http://api.openweathermap.org/data/2.5/";
    private static final String APP_ID = "46338a507923d7509aa9dfa643d7040b";
    private String city = "";
    private String requestType = "weather";

    public WeatherRequest(String city) {
        this.city = city;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public boolean isValidRequestType(String requestType) {
        String[] requestTypes = {"weather", "forecast"};
        return Arrays.asList(requestTypes).contains(requestType);
    }

    public JsonObject send() throws IOException {
        URL obj = null;
        String requestURL = this.getRequestUrl();
        try {
            obj = new URL(requestURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JsonParser().parse(response.toString()).getAsJsonObject();
    }

    public String getRequestUrl() {
        return URL_API + this.getRequestType() + "?q=" + this.city + "&appid=" + APP_ID + "&units=metric";
    }
}
