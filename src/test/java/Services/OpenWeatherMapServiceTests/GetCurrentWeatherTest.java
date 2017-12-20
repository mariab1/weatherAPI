package Services.OpenWeatherMapServiceTests;

import Exceptions.OpenWeatherMapAppIdNotSetException;
import Services.Entities.Coordinates;
import Services.Entities.Unit;
import Services.Entities.Reports.CurrentWeatherReport;
import org.junit.jupiter.api.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GetCurrentWeatherTest extends WeatherTest {
    private static CurrentWeatherReport report;

    @BeforeAll
    public static void setUpClass() throws IOException, OpenWeatherMapAppIdNotSetException {
        WeatherTest.setUpClass();
        if (useMocking) {
            when(service.getCurrentWeather(request))
                .thenReturn(
                    new CurrentWeatherReport(
                        "Tallinn",
                        new Coordinates(123, 31),
                        Unit.metric,
                        6
                    )
                );
        }
        report = service.getCurrentWeather(request);
    }

    @Test
    public void testIfResponseCityEqualsRequestCity() throws IOException {
        assertEquals(report.cityName, report.cityName);
    }

    @Test
    public void testIfWeatherResponseCityCoordinatesAreValid() throws Exception {
        this.validateCoordinates(report.cityCoordinates);
    }

    @Test
    public void testIfWeatherResponseCurrentTemperatureIsValid() throws Exception {
        this.validateTemperature(report.currentTemperature, request.unitSystem);
    }
}
