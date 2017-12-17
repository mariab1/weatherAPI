package Services.OpenWeatherMap;


import Exceptions.OpenWeatherMapAppIdNotSetException;
import Services.Contracts.WeatherServiceContract;
import Services.Entities.Coordinates;
import Services.Entities.DayWeatherForecast;
import Services.Entities.Reports.CurrentWeatherReport;
import Services.Entities.Reports.ThreeDayWeatherReport;
import Services.Entities.Requests.WeatherForecastRequest;
import Services.HttpRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class OpenWeatherMapService implements WeatherServiceContract {
    private final String serviceUrl = "http://api.openweathermap.org/data/2.5/";

    @Override
    public CurrentWeatherReport getCurrentWeather(WeatherForecastRequest request) throws OpenWeatherMapAppIdNotSetException, IOException {
        HttpRequest httpRequest = new HttpRequest();
        String requestUrl = this.getCurrentWeatherRequestUrl(request);
        String response = httpRequest.makeGetRequest(requestUrl);
        JsonObject result = new JsonParser().parse(response).getAsJsonObject();

        return new CurrentWeatherReport(
                result.get("name").getAsString(),
                new Coordinates(
                    result.getAsJsonObject("coord").get("lon").getAsFloat(),
                    result.getAsJsonObject("coord").get("lat").getAsFloat()
                ),
                request.unitSystem,
                result.getAsJsonObject("main").get("temp").getAsFloat()
        );
    }

    @Override
    public ThreeDayWeatherReport getThreeDayWeatherForecast(WeatherForecastRequest request) throws OpenWeatherMapAppIdNotSetException, IOException {
        URL obj = null;
        String requestURL = this.getForecastRequestUrl(request);
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

        JsonObject result = new JsonParser().parse(response.toString()).getAsJsonObject();

        int i = 1;
        ArrayList<DayWeatherForecast> forecasts = new ArrayList<>();
        for (JsonElement elem : result.getAsJsonArray("list")) {
            JsonObject currentForecast = elem.getAsJsonObject();
            if (i > 3) {
                break;
            }
            forecasts.add(new DayWeatherForecast(
                // x1000 kuna API tagastab UNIX timestampi, aga
                // java Date klass ootab millisekundeid.
                new Date(currentForecast.get("dt").getAsLong() * 1000),
                currentForecast.get("main").getAsJsonObject().get("temp_min").getAsFloat(),
                currentForecast.get("main").getAsJsonObject().get("temp_max").getAsFloat()
            ));
            i++;
        }

        return new ThreeDayWeatherReport(
            result.getAsJsonObject("city").get("name").getAsString(),
            new Coordinates(
                    result.getAsJsonObject("city").getAsJsonObject("coord").get("lon").getAsFloat(),
                    result.getAsJsonObject("city").getAsJsonObject("coord").get("lat").getAsFloat()
            ),
            request.unitSystem,
            forecasts
        );
    }

    private String getForecastRequestUrl(WeatherForecastRequest request) throws OpenWeatherMapAppIdNotSetException {
        return this.serviceUrl + "forecast?q=" + request.cityName + "," +
                request.countryCode + "&appid=" + this.getAppId() + "&units=" + request.unitSystem;
    }

    private String getCurrentWeatherRequestUrl(WeatherForecastRequest request) throws OpenWeatherMapAppIdNotSetException {
        return this.serviceUrl + "weather?q=" + request.cityName + "," +
                request.countryCode + "&appid=" + this.getAppId() + "&units=" + request.unitSystem;
    }

    public String getAppId() throws OpenWeatherMapAppIdNotSetException {
        String appId = this.getEnvironmentVariable("owm_app_id");
        if (appId == null) {
            throw new OpenWeatherMapAppIdNotSetException("OWM APP ID-d ei leitud süsteemi muutujate hulgast");
        }
        return appId;
    }

    public String getEnvironmentVariable(String name) {
        return System.getenv(name);
    }
}
