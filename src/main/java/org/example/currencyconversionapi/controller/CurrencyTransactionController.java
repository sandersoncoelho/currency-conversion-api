package org.example.currencyconversionapi.controller;

import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/currency-transaction")
public class CurrencyTransactionController {

    @GetMapping
    private Flux<CurrencyTransaction> getAllCurrencyTransactions() {
        CurrencyTransaction c = new CurrencyTransaction();
        c.setCurrencyTransactionId(12L);
        c.setUserId(13L);
        return Flux.just(c);
    }
}
