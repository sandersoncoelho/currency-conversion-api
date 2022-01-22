package org.example.currencyconversionapi.service;

import org.example.currencyconversionapi.enums.Currency;
import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.example.currencyconversionapi.repository.CurrencyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Service
public class CurrencyConversionService {

    @Autowired
    private ExchangeRateApiService exchangeRateApiService;

    @Autowired
    private CurrencyTransactionRepository currencyTransactionRepository;

    public Mono<CurrencyTransaction> makeConversion(Long userId, String originCurrency, BigDecimal originValue,
                                                    String destinationCurrency) {
        return exchangeRateApiService.getConversion(getCurrency(originCurrency))
                .flatMap(exchangeRateApiResponse -> {
                    BigDecimal conversionRate = exchangeRateApiResponse.getRates().get(destinationCurrency);

                    CurrencyTransaction currencyTransaction = new CurrencyTransaction();
                    currencyTransaction.setUserId(userId);
                    currencyTransaction.setOriginCurrency(originCurrency);
                    currencyTransaction.setOriginValue(originValue);
                    currencyTransaction.setDestinationCurrency(destinationCurrency);
                    currencyTransaction.setDestinationValue(conversionRate.multiply(originValue));
                    currencyTransaction.setConversionRate(conversionRate);
                    currencyTransaction.setDateTimeTransaction(Instant.now());

                    return Mono.just(currencyTransactionRepository.save(currencyTransaction));
        });
    }

    private Currency getCurrency(String currencyValue) {
        Optional<Currency> currencyOptional = Currency.getCurrency(currencyValue);
        return currencyOptional.orElse(null);
    }

    public Flux<CurrencyTransaction> getAllCurrencyTransactions(Long userId) {
        return Flux.fromIterable(currencyTransactionRepository.findByUserId(userId));
    }
}
