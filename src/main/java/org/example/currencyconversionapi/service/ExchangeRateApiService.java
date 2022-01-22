package org.example.currencyconversionapi.service;

import org.example.currencyconversionapi.enums.Currency;
import org.example.currencyconversionapi.model.exchangerateapi.ExchangeRateApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateApiService {

    @Autowired
    private WebClient webClient;

    @Autowired
    @Qualifier("exchangeRateApiDefaultQueryParams")
    private MultiValueMap<String, String> exchangeRateApiDefaultQueryParams;

    public Mono<ExchangeRateApiResponse> getConversion(Currency currency) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                .queryParams(exchangeRateApiDefaultQueryParams)
                .queryParam("base", currency.getValue())
                .build())
                .retrieve().bodyToMono(ExchangeRateApiResponse.class);
    }
}
