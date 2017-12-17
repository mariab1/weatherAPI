package Exceptions;

public class OpenWeatherMapAppIdNotFoundException extends Exception {
    public OpenWeatherMapAppIdNotFoundException(String errorMessage) {
        super("Unexpected error occurred: " + errorMessage);
    }
}

