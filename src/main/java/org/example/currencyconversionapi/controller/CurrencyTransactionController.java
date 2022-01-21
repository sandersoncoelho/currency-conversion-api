package org.example.currencyconversionapi.controller;

import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.example.currencyconversionapi.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/currency-transaction")
public class CurrencyTransactionController {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping("/user/{userId}/origin/{originCurrency}/destination/{destinationCurrency}")
    public Mono<CurrencyTransaction> makeConversion(@PathVariable("userId") Long userId,
                                                    @PathVariable("originCurrency") String originCurrency,
                                                    @PathVariable("destinationCurrency") String destinationCurrency) {
        return currencyConversionService.makeConversion(userId, originCurrency, destinationCurrency);
    }

    @GetMapping("/user/{userId}")
    public Flux<CurrencyTransaction> getAllCurrencyTransactions(@PathVariable("userId") Long userId) {
        return currencyConversionService.getAllCurrencyTransactions(userId);
    }
}
