import Exceptions.OpenWeatherMapAppIdNotSetException;
import Services.Entities.CountryCode;
import Services.Entities.DayWeatherForecast;
import Services.Entities.Reports.CurrentWeatherReport;
import Services.Entities.Reports.ThreeDayWeatherReport;
import Services.Entities.Requests.WeatherForecastRequest;
import Services.Entities.Unit;
import Services.OpenWeatherMap.OpenWeatherMapService;

import java.io.IOException;
import java.util.*;

public class WeatherAPI {

    public static void main(String[] args) throws Exception {
        String userChoice;
        InputProvider input = new InputProvider();

        Scanner in = new Scanner(System.in);
        System.out.print("Kas lugeda sisend failist[f] või kasutaja sisendist[s]: ");
        userChoice = in.next();
        if (userChoice.equals("s")) {
            System.out.print("Sisesta linn (näit Tallinn): ");
            String city = input.getCityFromUserInput(System.in);
            processCity(city);
        } else if(userChoice.equals("f")) {
            for (String city : input.getCitiesFromDataStream(input.getFileStream("input.txt"))) {
                processCity(city);
            }
        } else {
            System.out.println("Vale sisend");
        }

    }

    public static void processCity(String city) throws IOException, OpenWeatherMapAppIdNotSetException {
        OutputProvider output = new OutputProvider();
        ArrayList<String> rows = new ArrayList<>();
        OpenWeatherMapService service = new OpenWeatherMapService();
        String outputFile = city + ".txt";

        WeatherForecastRequest request = new WeatherForecastRequest(city, CountryCode.EE, Unit.metric);
        CurrentWeatherReport weatherReport = service.getCurrentWeather(request);
        rows.add("Hetkel linnas (" + weatherReport.cityName + ") olev temperatuur: " + weatherReport.currentTemperature);

        rows.add("Järgmise kolme päeva ennustus:");
        ThreeDayWeatherReport report = service.getThreeDayWeatherForecast(request);
        for (DayWeatherForecast dayForecast : report.dayForecasts) {
            rows.add("Min. " + dayForecast.minTemperature + " Max." + dayForecast.maxTemperature);
        }

        output.toFile(outputFile, rows);
        System.out.println("Tulemus kirjutatud faili " + outputFile);
    }
}