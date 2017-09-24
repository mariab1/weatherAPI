import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherAPITest {
    public String apiUrl = "http://api.openweathermap.org/data/2.5/weather";

    @Test
    public void apiIsAccessible() throws IOException {
        URL obj = new URL(this.apiUrl);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        //System.out.println(responseCode); // 401 - unauthorized
        assertEquals(200, responseCode);
    }

    @Test
    public void currentTemperatureIsInValidFormat() {
        assertEquals('C', 'F');
    }

    @Test
    public void coordinatesAreInCorrectFormat() {
        assertEquals("xxx:yyy", "xx:yy");
    }
}