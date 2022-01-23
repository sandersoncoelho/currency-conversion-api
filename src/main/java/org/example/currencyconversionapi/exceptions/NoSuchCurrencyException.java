package org.example.currencyconversionapi.exceptions;

public class NoSuchCurrencyException extends Exception {
    private String message;

    public NoSuchCurrencyException() {
    }

    public NoSuchCurrencyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
