package org.example.currencyconversionapi.service;

import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyConversionService {

    public Mono<CurrencyTransaction> makeConversion(Long userId, String originCurrency, String destinationCurrency) {
        CurrencyTransaction c = new CurrencyTransaction();
        c.setCurrencyTransactionId(12L);
        c.setUserId(13L);
        return Mono.just(c);
    }

    public Flux<CurrencyTransaction> getAllCurrencyTransactions(Long userId) {
        CurrencyTransaction c = new CurrencyTransaction();
        c.setCurrencyTransactionId(12L);
        c.setUserId(13L);
        return Flux.just(c);
    }
}
