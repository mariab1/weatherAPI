package Services.OpenWeatherMapServiceTests;

import Exceptions.InvalidDateException;
import Exceptions.OpenWeatherMapAppIdNotSetException;
import Services.Entities.Coordinates;
import Services.Entities.CountryCode;
import Services.Entities.Requests.WeatherForecastRequest;
import Services.Entities.Unit;
import Services.OpenWeatherMap.OpenWeatherMapService;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;

public class WeatherTest {
    protected static final boolean useMocking = true;
    protected static OpenWeatherMapService service;
    protected static WeatherForecastRequest request;

    @BeforeAll
    public static void setUpClass() throws IOException, OpenWeatherMapAppIdNotSetException {
        request = new WeatherForecastRequest("Tallinn", CountryCode.EE, Unit.metric);
        if (useMocking) {
            service = Mockito.mock(OpenWeatherMapService.class);
            return;
        }
        service = new OpenWeatherMapService();
    }

    public void validateCoordinates(Coordinates coordinates) throws Exception {
        double latMax = 90;
        double latMin = -90;
        double lngMax = 180;
        double lngMin = -180;

        if (coordinates == null) { //  || coordinates.latitude == NO_VALUE || coordinates.longitude == NO_VALUE
            throw new Exception("Geo-coordinates must be specified");
        }
        if (coordinates.latitude > latMax || coordinates.latitude<latMin) {
            throw new Exception("Geo-coordinates latitude is not valid");
        }
        if (coordinates.longitude > lngMax ||  coordinates.longitude<lngMin) {
            throw new Exception("Geo-coordinates longitude is not valid");
        }
    }


    public void validateTemperature(Float tempCurrent, Unit unitSystem) throws Exception {
        float maxTemp = 60;
        float minTemp = -70;

        if (unitSystem.equals(Unit.metric)) {
            maxTemp = 60;
            minTemp = -70;
        }
        if(unitSystem.equals(Unit.imperial)) {
            maxTemp = 140;
            minTemp = -94;
        }

        if (tempCurrent == null) {
            throw new Exception("Temperature is NIL: " + tempCurrent);
        }
        if (tempCurrent < minTemp) {
            throw new Exception("Temperature cannot be lower than: " + minTemp + ", report shows: " + tempCurrent);
        }
        if (tempCurrent > maxTemp) {
            throw new Exception("Temperature cannot be higher than: " + maxTemp + ", report shows: " + tempCurrent);
        }
    }

    public void validateDate(Date date) throws Exception {
        Integer actualYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(date));
        if (actualYear < Year.now().getValue()) {
            throw new InvalidDateException("Incorrect year found: "  + actualYear.toString());
        }
    }
}
