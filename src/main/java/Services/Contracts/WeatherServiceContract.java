package Services.Contracts;

import Exceptions.OpenWeatherMapAppIdNotSetException;

import Services.Entities.Requests.WeatherForecastRequest;
import Services.Entities.Reports.CurrentWeatherReport;
import Services.Entities.Reports.ThreeDayWeatherReport;

import java.io.IOException;

public interface WeatherServiceContract {
    CurrentWeatherReport getCurrentWeather(WeatherForecastRequest request) throws OpenWeatherMapAppIdNotSetException, IOException;
    ThreeDayWeatherReport getThreeDayWeatherForecast(WeatherForecastRequest request) throws OpenWeatherMapAppIdNotSetException, IOException;
}
