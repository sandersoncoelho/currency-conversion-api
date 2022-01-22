package org.example.currencyconversionapi.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Currency {
    BRL("BRL"),
    USD("USD"),
    EUR("EUR"),
    JPY("JPY");

    private String value;

    private Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<Currency> getCurrency(String value) {
        return Arrays.stream(values()).filter(currency -> currency.value.equals(value)).findFirst();
    }
}
