package org.example.currencyconversionapi.service;

import org.example.currencyconversionapi.enums.Currency;
import org.example.currencyconversionapi.model.exchangerateapi.ExchangeRateApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateApiService {

    @Autowired
    private WebClient webClient;

    public Mono<ExchangeRateApiResponse> getConversion(Currency currency) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                    .queryParam("base", currency.getValue())
                    .build())
                .retrieve().bodyToMono(ExchangeRateApiResponse.class);
    }
}
