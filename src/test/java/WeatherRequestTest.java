import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

public class WeatherRequestTest {
    @Test
    public void testIfapiIsAccessible() throws IOException {
        WeatherRequest wr = new WeatherRequest("Tallinn,EE");
        String requestUrl = wr.getRequestUrl();
        URL obj = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);
    }

    @Test
    public void testIfIsValidRequestType() throws Exception {
        WeatherRequest wr = new WeatherRequest("Tallinn,EE");
        assertTrue(wr.isValidRequestType(wr.getRequestType()));

        wr.setRequestType("forecast");
        assertTrue(wr.isValidRequestType(wr.getRequestType()));

        wr.setRequestType("notExistingRequestType");
        assertFalse(wr.isValidRequestType(wr.getRequestType()));
    }

    @Test
    public void send() throws Exception {
    }

}