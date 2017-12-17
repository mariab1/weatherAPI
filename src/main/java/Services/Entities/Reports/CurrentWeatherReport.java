package Services.Entities.Reports;

import Services.Entities.Coordinates;
import Services.Entities.Unit;

public class CurrentWeatherReport extends WeatherReport {
    public final float currentTemperature;

    public CurrentWeatherReport(String cityName, Coordinates cityCoordinates, Unit unitSystem, float currentTemperature) {
        super(cityName, cityCoordinates, unitSystem);
        this.currentTemperature = currentTemperature;
    }
}