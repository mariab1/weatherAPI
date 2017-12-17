package Exceptions;

public class OpenWeatherMapAppIdNotSetException extends Exception {
    public OpenWeatherMapAppIdNotSetException(String errorMessage) {
        super("Unexpected error occurred: " + errorMessage);
    }
}
