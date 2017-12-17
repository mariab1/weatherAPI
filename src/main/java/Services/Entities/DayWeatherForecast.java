package Services.Entities;

import java.util.Date;

public class DayWeatherForecast {
    public final Date date;
    public final float minTemperature;
    public final float maxTemperature;

    public DayWeatherForecast(Date date, float minTemperature, float maxTemperature) {
        this.date = date;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
}
