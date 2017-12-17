package Exceptions;

public class WeatherForecastNotFoundException extends Exception {
    public WeatherForecastNotFoundException(String errorMessage) {
        super("Unexpected error occurred: " + errorMessage);
    }
}
