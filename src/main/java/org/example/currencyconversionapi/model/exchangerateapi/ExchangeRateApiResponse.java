package org.example.currencyconversionapi.model.exchangerateapi;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class ExchangeRateApiResponse {
    private Boolean success;
    private Instant timestamp;
    private String base;
    private LocalDate date;
    private LinkedHashMap<String, BigDecimal> rates;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LinkedHashMap<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(LinkedHashMap<String, BigDecimal> rates) {
        this.rates = rates;
    }
}
