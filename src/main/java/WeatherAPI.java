import java.io.IOException;
import java.util.*;

public class WeatherAPI {

    public static void main(String[] args) throws IOException {
        String userChoice;
        String city = "";
        String outputFile = "output.txt";
        InputProvider input = new InputProvider();
        OutputProvider output = new OutputProvider();
        ArrayList<String> rows = new ArrayList<String>();

        Scanner in = new Scanner(System.in);
        System.out.print("Kas lugeda sisend failist[f] või kasutaja sisendist[s]: ");
        userChoice = in.next();
        if (userChoice.equals("s")) {
            city = input.getCityFromUserInput();
        } else if(userChoice.equals("f")) {
            city = input.getCityFromDataFile("input.txt");
        } else {
            System.out.println("Vale sisend");
        }


        OpenWeatherMap owm = new OpenWeatherMap();
        float currentTemperature = owm.currentTemperatureByCity(city);
        rows.add("Hetkel linnas (" + city + ") olev temperatuur: " + currentTemperature);
        rows.add("Järgmise kolme päeva ennustus:");
        List<WeatherForecast> forecasts = owm.dailyForecastByCity(city, 3);
        for (WeatherForecast forecast : forecasts) {
            rows.add("Min. " + forecast.tempMin + " Max." + forecast.tempMax);
        }

        output.toFile(outputFile, rows);
        System.out.print("Tulemus kirjutatud faili " + outputFile);
    }
}