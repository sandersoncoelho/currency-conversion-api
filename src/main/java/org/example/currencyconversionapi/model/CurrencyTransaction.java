package org.example.currencyconversionapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CurrencyTransaction {
    private Long currencyTransactionId;
    private Long userId;
    private String originCurrency;
    private BigDecimal originValue;
    private String destinationCurrency;
    private BigDecimal conversionRate;
    private LocalDateTime dateTimeTransaction;

    public Long getCurrencyTransactionId() {
        return currencyTransactionId;
    }

    public void setCurrencyTransactionId(Long currencyTransactionId) {
        this.currencyTransactionId = currencyTransactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOriginCurrency() {
        return originCurrency;
    }

    public void setOriginCurrency(String originCurrency) {
        this.originCurrency = originCurrency;
    }

    public BigDecimal getOriginValue() {
        return originValue;
    }

    public void setOriginValue(BigDecimal originValue) {
        this.originValue = originValue;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public LocalDateTime getDateTimeTransaction() {
        return dateTimeTransaction;
    }

    public void setDateTimeTransaction(LocalDateTime dateTimeTransaction) {
        this.dateTimeTransaction = dateTimeTransaction;
    }
}
