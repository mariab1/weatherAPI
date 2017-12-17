package Services.Contracts;

import Exceptions.WeatherForecastNotFoundException;

import Services.Entities.Requests.WeatherForecastRequest;
import Services.Entities.Reports.CurrentWeatherReport;
import Services.Entities.Reports.ThreeDayWeatherReport;

import java.io.IOException;

public interface WeatherServiceContract {
    CurrentWeatherReport getCurrentWeather(WeatherForecastRequest request) throws WeatherForecastNotFoundException, IOException;
    ThreeDayWeatherReport getThreeDayWeatherForecast(WeatherForecastRequest request) throws WeatherForecastNotFoundException, IOException;
}
