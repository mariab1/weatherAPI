package Services.OpenWeatherMapServiceTests;

import Exceptions.OpenWeatherMapAppIdNotSetException;
import Services.Entities.Coordinates;
import Services.Entities.DayWeatherForecast;
import Services.Entities.Reports.ThreeDayWeatherReport;
import Services.Entities.Unit;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetThreeDayWeatherForecastTest extends WeatherTest {
    private static ThreeDayWeatherReport report;

    @BeforeAll
    public static void setUpClass() throws IOException, OpenWeatherMapAppIdNotSetException {
        WeatherTest.setUpClass();
        if (useMocking) {
            ArrayList<DayWeatherForecast> dayForecasts = new ArrayList<>();
            dayForecasts.add(new DayWeatherForecast(new Date(), -1, 4));
            dayForecasts.add(new DayWeatherForecast(new Date(), 0, 3));
            dayForecasts.add(new DayWeatherForecast(new Date(), -2, 1));

            when(service.getThreeDayWeatherForecast(request))
                .thenReturn(
                    new ThreeDayWeatherReport(
                        "Tallinn",
                        new Coordinates(123.24, 31.43),
                        Unit.metric,
                        dayForecasts
                    )
                );
        }
        report = service.getThreeDayWeatherForecast(request);
    }

    @Test
    public void testIfResponseCityEqualsRequestCity() {
        assertEquals(report.cityName, request.cityName);
    }

    @Test
    public void testIfWeatherRepoResponseCityCoordinatesAreaValid() throws Exception {
        this.validateCoordinates(report.cityCoordinates);
    }


    @Test
    public void testIfForecastReturnsWeatherForSpecifiedNrOfDays() {
        int nrOfDaysRequested = 3;
        assertEquals(nrOfDaysRequested, report.dayForecasts.size());
    }

    @Test
    public void testIfForecastResponseDailyLowsAreValid() throws Exception {
        for (DayWeatherForecast dayForecast : report.dayForecasts) {
            this.validateTemperature(dayForecast.minTemperature, report.unitSystem);
        }
    }

    @Test
    public void testIfForecastResponseDailyHighsAreValid() throws Exception {
        for (DayWeatherForecast dayForecast : report.dayForecasts) {
            this.validateTemperature(dayForecast.maxTemperature, report.unitSystem);
        }
    }

    @Test
    public void testIfForecastResponseDatesAreValid() throws Exception {
        for (DayWeatherForecast dayForecast : report.dayForecasts) {
            this.validateDate(dayForecast.date);
        }
    }
}
