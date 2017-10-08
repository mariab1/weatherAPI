import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class OpenWeatherMap {
    private String city = "";
    private int days = 1;

    public List<WeatherForecast> dailyForecastByCity(String city, int days) throws IOException {
        WeatherRequest wr = new WeatherRequest(city);
        wr.setRequestType("forecast");
        JsonObject jsonObject = wr.send();

        int i = 1;
        List<WeatherForecast> forecasts = new ArrayList<WeatherForecast>();
        for (JsonElement obj : jsonObject.getAsJsonArray("list")) {
            JsonObject currentForecast = obj.getAsJsonObject();
            if (i > days) {
                break;
            }
            //System.out.println(obj.toString());
            forecasts.add(new WeatherForecast(
                currentForecast.get("dt").getAsInt(),
                currentForecast.get("main").getAsJsonObject().get("temp_min").getAsFloat(),
                currentForecast.get("main").getAsJsonObject().get("temp_max").getAsFloat()
            ));
            i++;
        }
        return forecasts;
    }

    public float currentTemperatureByCity(String city) throws IOException {
        JsonObject jsonObject = (new WeatherRequest(city)).send();
        return jsonObject.getAsJsonObject("main").get("temp").getAsFloat();
    }
}
