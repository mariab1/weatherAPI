package Services.OpenWeatherMapServiceTests;

import Exceptions.WeatherForecastNotFoundException;
import Services.Entities.CountryCode;
import Services.Entities.DayWeatherForecast;
import Services.Entities.Reports.ThreeDayWeatherReport;
import Services.Entities.Requests.WeatherForecastRequest;
import Services.Entities.Unit;
import Services.OpenWeatherMap.OpenWeatherMapService;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetThreeDayWeatherForecastTest extends WeatherTest {
    private final String testCity = "Tallinn";
    private final CountryCode testCountryCode = CountryCode.EE;
    private final Unit testUnitSystem = Unit.metric;

    @Test
    public void testIfResponseCityEqualsRequestCity() throws IOException, WeatherForecastNotFoundException {
        // [given]
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest(testCity, testCountryCode, testUnitSystem);
        // [when]
        ThreeDayWeatherReport report = service.getThreeDayWeatherForecast(request);
        // [then]
        assertEquals(report.cityName, request.cityName);
    }

    @Test
    public void testIfWeatherRepoResponseCityCoordinatesAreaValid() throws Exception {
        // [given]
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest(testCity, testCountryCode, testUnitSystem);
        // [when]
        ThreeDayWeatherReport report = service.getThreeDayWeatherForecast(request);
        // [then]
        this.validateCoordinates(report.cityCoordinates);
    }


    @Test
    public void testIfForecastReturnsWeatherForSpecifiedNrOfDays() throws IOException, WeatherForecastNotFoundException {
        // [given]
        int nrOfDaysRequested = 3;
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest(testCity, testCountryCode, testUnitSystem);
        // [when]
        ThreeDayWeatherReport report = service.getThreeDayWeatherForecast(request);
        // [then]
        assertEquals(nrOfDaysRequested, report.dayForecasts.size());
    }

    @Test
    public void testIfForecastResponseDailyLowsAreValid() throws Exception {
        // [given]
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest(testCity, testCountryCode, testUnitSystem);
        // [when]
        ThreeDayWeatherReport report = service.getThreeDayWeatherForecast(request);
        for (DayWeatherForecast dayForecast : report.dayForecasts) {
            this.validateTemperature(dayForecast.minTemperature, report.unitSystem);
        }
    }

    @Test
    public void testIfForecastResponseDailyHighsAreValid() throws Exception {
        // [given]
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest(testCity, testCountryCode, testUnitSystem);
        // [when]
        ThreeDayWeatherReport report = service.getThreeDayWeatherForecast(request);
        // [then]
        for (DayWeatherForecast dayForecast : report.dayForecasts) {
            this.validateTemperature(dayForecast.maxTemperature, report.unitSystem);
        }
    }

    @Test
    public void testIfForecastResponseDatesAreValid() throws Exception {
        // [given]
        OpenWeatherMapService service = new OpenWeatherMapService();
        WeatherForecastRequest request = new WeatherForecastRequest(testCity, testCountryCode, testUnitSystem);
        // [when]
        ThreeDayWeatherReport report = service.getThreeDayWeatherForecast(request);
        // [then]
        for (DayWeatherForecast dayForecast : report.dayForecasts) {
            this.validateDate(dayForecast.date);
        }
    }
}
