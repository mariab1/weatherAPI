import Exceptions.WeatherForecastNotFoundException;
import Services.Entities.CountryCode;
import Services.Entities.DayWeatherForecast;
import Services.Entities.Reports.CurrentWeatherReport;
import Services.Entities.Requests.WeatherForecastRequest;
import Services.Entities.Unit;
import Services.OpenWeatherMap.OpenWeatherMapService;

import java.io.IOException;
import java.util.*;

public class WeatherAPI {

    public static void main(String[] args) throws IOException, WeatherForecastNotFoundException {
/*
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
        */

        OpenWeatherMapService weatherService = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest("Tallinn", CountryCode.EE, Unit.metric);

        CurrentWeatherReport report = weatherService.getCurrentWeather(request);
        System.out.println(report.cityName);
        System.out.println(report.cityCoordinates);
        System.out.println(report.unitSystem);
        System.out.println(report.currentTemperature);
        /*
        String userChoice;
        InputProvider input = new InputProvider();

        Scanner in = new Scanner(System.in);
        System.out.print("Kas lugeda sisend failist[f] või kasutaja sisendist[s]: ");
        userChoice = in.next();
        if (userChoice.equals("s")) {
            String city = input.getCityFromUserInput();
            processCity(city);
        } else if(userChoice.equals("f")) {
            for (String city : input.getCitiesFromDataFile("input.txt")) {
                processCity(city);
            }
        } else {
            System.out.println("Vale sisend");
        }
        */
    }

    public static void processCity(String city) throws IOException {
        OutputProvider output = new OutputProvider();
        ArrayList<String> rows = new ArrayList<>();
        OpenWeatherMap owm = new OpenWeatherMap();
        String outputFile = city + ".txt";

        float currentTemperature = owm.currentTemperatureByCity(city);
        rows.add("Hetkel linnas (" + city + ") olev temperatuur: " + currentTemperature);
        rows.add("Järgmise kolme päeva ennustus:");
        List<DayWeatherForecast> forecasts = owm.dailyForecastByCity(city, 3);
        for (DayWeatherForecast forecast : forecasts) {
            rows.add("Min. " + forecast.minTemperature + " Max." + forecast.maxTemperature);
        }

        output.toFile(outputFile, rows);
        System.out.println("Tulemus kirjutatud faili " + outputFile);
    }
}