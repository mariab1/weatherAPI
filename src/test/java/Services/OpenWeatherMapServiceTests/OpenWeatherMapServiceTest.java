package Services.OpenWeatherMapServiceTests;

import Exceptions.OpenWeatherMapAppIdNotSetException;
import Services.OpenWeatherMap.OpenWeatherMapService;
import org.mockito.Mockito;

import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

public class OpenWeatherMapServiceTest {
    @Test
    public void testIfAppIdCanBeAccessedFromEnv() throws OpenWeatherMapAppIdNotSetException {
        String testAppId = "a98yf";
        OpenWeatherMapService service = new OpenWeatherMapService();
        OpenWeatherMapService serviceToTestSpy = Mockito.spy(service);
        Mockito.when(serviceToTestSpy.getEnvironmentVariable("owm_app_id")).thenReturn(testAppId);
        assertEquals(testAppId, serviceToTestSpy.getAppId());
    }
}
