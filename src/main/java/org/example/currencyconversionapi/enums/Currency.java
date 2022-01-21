package org.example.currencyconversionapi.enums;

public enum Currency {
    BRL("BRL"),
    USD("USD"),
    EUR("EUR"),
    JPY("JPY");

    private String value;
    private Currency(String value) {
        this.value = value;
    }
}
