package org.example.currencyconversionapi.service;

import org.example.currencyconversionapi.enums.Currency;
import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.example.currencyconversionapi.model.exchangerateapi.ExchangeRateApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class CurrencyConversionService {
    public Mono<CurrencyTransaction> makeConversion(Long userId, String originCurrency, BigDecimal originValue,
                                                    String destinationCurrency) {
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
