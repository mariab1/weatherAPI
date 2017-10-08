import java.io.IOException;
import java.util.List;

public class WeatherAPI {

    public static void main(String[] args) throws IOException {
        System.out.print("Hetkel Tallinnas olev temperatuur: ");
        OpenWeatherMap owm = new OpenWeatherMap();
        float currentTemperature = owm.currentTemperatureByCity("Tallinn,EE");
        System.out.println(currentTemperature);

        System.out.println("Järgmise kolme päeva ennustus:");
        List<WeatherForecast> forecasts = owm.dailyForecastByCity("Tallinn,EE", 3);
        for (WeatherForecast forecast : forecasts) {
            System.out.println(forecast.dt);
            System.out.println(forecast.tempMin);
            System.out.println(forecast.tempMax);
        }
    }
}
