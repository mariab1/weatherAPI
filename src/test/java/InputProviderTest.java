import org.junit.jupiter.api.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InputProviderTest {
    @Test
    public void testIfCityCanBeEnteredFromUserInput() {
        // [given]
        InputProvider provider = new InputProvider();
        // [when]
        String city = provider.getCityFromUserInput(
            new ByteArrayInputStream("Tallinn".getBytes())
        );
        // [then]
        assertEquals("Tallinn", city);
    }

    @Test
    public void testIfCitiesListCanBeTakenFromDataStream() throws Exception {
        // [given]
        InputProvider inputProvider = new InputProvider();
        BufferedReader stream = new BufferedReader(new StringReader("Tallinn\nTartu"));
        // [when]
        ArrayList<String> cities = inputProvider.getCitiesFromDataStream(stream);
        // [then]
        ArrayList<String> expectedCities = new ArrayList<>();
        expectedCities.add("Tallinn");
        expectedCities.add("Tartu");
        assertEquals(expectedCities, cities);
    }
}
