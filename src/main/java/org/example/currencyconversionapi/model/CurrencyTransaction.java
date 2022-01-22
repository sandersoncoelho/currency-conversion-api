package org.example.currencyconversionapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class CurrencyTransaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long currencyTransactionId;
    private Long userId;
    private String originCurrency;
    private BigDecimal originValue;
    private String destinationCurrency;
    private BigDecimal destinationValue;
    private BigDecimal conversionRate;
    private Instant dateTimeTransaction;

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

    public BigDecimal getDestinationValue() {
        return destinationValue;
    }

    public void setDestinationValue(BigDecimal destinationValue) {
        this.destinationValue = destinationValue;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Instant getDateTimeTransaction() {
        return dateTimeTransaction;
    }

    public void setDateTimeTransaction(Instant dateTimeTransaction) {
        this.dateTimeTransaction = dateTimeTransaction;
    }
}
