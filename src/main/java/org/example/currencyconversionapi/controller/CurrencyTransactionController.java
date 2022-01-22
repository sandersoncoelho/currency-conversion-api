package org.example.currencyconversionapi.controller;

import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.example.currencyconversionapi.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-transaction")
public class CurrencyTransactionController {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping("/makeConversion")
    public Mono<CurrencyTransaction> makeConversion(@RequestParam("userId") Long userId,
                                                    @RequestParam("originCurrency") String originCurrency,
                                                    @RequestParam("originValue") BigDecimal originValue,
                                                    @RequestParam("destinationCurrency") String destinationCurrency) {
        return currencyConversionService.makeConversion(userId, originCurrency, originValue, destinationCurrency);
    }

    @GetMapping("/user/{userId}")
    public Flux<CurrencyTransaction> getAllCurrencyTransactions(@PathVariable("userId") Long userId) {
        return currencyConversionService.getAllCurrencyTransactions(userId);
    }
}
