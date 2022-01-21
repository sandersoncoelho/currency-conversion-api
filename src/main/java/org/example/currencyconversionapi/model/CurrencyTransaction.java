package org.example.currencyconversionapi.model;

public class CurrencyTransaction {
    private Long currencyTransactionId;
    private Long userId;

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
}
