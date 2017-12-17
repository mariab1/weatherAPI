package Services.OpenWeatherMapServiceTests;

import Exceptions.WeatherForecastNotFoundException;
import Services.Entities.CountryCode;
import Services.Entities.Requests.WeatherForecastRequest;
import Services.Entities.Unit;
import Services.OpenWeatherMap.OpenWeatherMapService;
import Services.Entities.Reports.CurrentWeatherReport;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GetCurrentWeatherTest extends WeatherTest {
    @Test
    public void testIfResponseCityEqualsRequestCity() throws IOException, WeatherForecastNotFoundException {
        // [given]
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest("Tallinn", CountryCode.EE, Unit.metric);
        // [when]
        CurrentWeatherReport report = service.getCurrentWeather(request);
        // [then]
        assertEquals(report.cityName, report.cityName);
    }

    @Test
    public void testIfWeatherResponseCityCoordinatesAreValid() throws Exception {
        // [given]
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest("Tallinn", CountryCode.EE, Unit.metric);
        // [when]
        CurrentWeatherReport report = service.getCurrentWeather(request);
        // [then]
        this.validateCoordinates(report.cityCoordinates);
    }

    @Test
    public void testIfWeatherResponseCurrentTemperatureIsValid() throws Exception {
        // [given]
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest("Tallinn", CountryCode.EE, Unit.metric);
        // [when]
        CurrentWeatherReport report = service.getCurrentWeather(request);
        // [then]
        this.validateTemperature(report.currentTemperature, request.unitSystem);
    }
}
