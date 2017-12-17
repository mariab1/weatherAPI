package Services.Entities.Reports;

import Services.Entities.Coordinates;
import Services.Entities.Unit;

class WeatherReport {
    public final String cityName;
    public final Coordinates cityCoordinates;
    public final Unit unitSystem;

    public WeatherReport(String cityName, Coordinates cityCoordinates, Unit unitSystem) {
        this.cityName = cityName;
        this.cityCoordinates = cityCoordinates;
        this.unitSystem = unitSystem;
    }
}