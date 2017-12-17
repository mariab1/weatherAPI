package Services.Entities.Reports;

import Services.Entities.Coordinates;
import Services.Entities.DayWeatherForecast;
import Services.Entities.Unit;

import java.util.ArrayList;

public class ThreeDayWeatherReport extends WeatherReport {
    public final ArrayList<DayWeatherForecast> dayForecasts;

    public ThreeDayWeatherReport(String cityName, Coordinates cityCoordinates, Unit units, ArrayList<DayWeatherForecast> dayForecasts) {
        super(cityName, cityCoordinates, units);
        this.dayForecasts = dayForecasts;
    }
}
