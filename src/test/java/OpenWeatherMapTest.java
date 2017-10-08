import org.junit.Test;

import static org.junit.Assert.*;

public class OpenWeatherMapTest {
    @Test
    public void dailyForecastByCity() throws Exception {
    }

    @Test
    public void currentTemperatureByCityResultIsInCorrectFormat() throws Exception {
        OpenWeatherMap owm = new OpenWeatherMap();
        float result = owm.currentTemperatureByCity("Tallinn,EE");

        assertTrue(result < 100);
        assertTrue(result > -50);
    }

}