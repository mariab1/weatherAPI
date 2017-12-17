import Services.Entities.DayWeatherForecast;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.*;

public class OpenWeatherMap {
    private String city = "";
    private int days = 1;

    public List<DayWeatherForecast> dailyForecastByCity(String city, int days) throws IOException {
        WeatherRequest wr = new WeatherRequest(city);
        wr.setRequestType("forecast");
        JsonObject jsonObject = wr.send();

        int i = 1;
        List<DayWeatherForecast> forecasts = new ArrayList<>();
        for (JsonElement obj : jsonObject.getAsJsonArray("list")) {
            JsonObject currentForecast = obj.getAsJsonObject();
            if (i > days) {
                break;
            }
            //System.out.println(obj.toString());
            forecasts.add(new DayWeatherForecast(
                //currentForecast.get("dt").(),
                    new Date(),
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
