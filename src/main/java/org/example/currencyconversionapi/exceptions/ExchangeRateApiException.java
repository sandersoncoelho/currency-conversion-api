package org.example.currencyconversionapi.exceptions;

public class ExchangeRateApiException extends Exception {
    private String message;

    public ExchangeRateApiException(String message) {
        this.message = message;
    }

    public ExchangeRateApiException() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
