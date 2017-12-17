package Services.Entities.Requests;

import Services.Entities.CountryCode;
import Services.Entities.Unit;

public class WeatherForecastRequest {
    public final String cityName;
    public final Unit unitSystem;
    public final CountryCode countryCode;


    public WeatherForecastRequest(String cityName, CountryCode countryCode, Unit unitSystem) {
        this.cityName = cityName;
        this.unitSystem = unitSystem;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "WeatherForecastRequest{" +
                "cityName='" + cityName + '\'' +
                ", unitSystem=" + unitSystem +
                ", countryCode=" + countryCode +
                '}';
    }
}
