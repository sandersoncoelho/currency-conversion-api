package org.example.currencyconversionapi.controller;

import org.example.currencyconversionapi.enums.Currency;
import org.example.currencyconversionapi.exceptions.ExchangeRateApiException;
import org.example.currencyconversionapi.exceptions.NoSuchCurrencyException;
import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.example.currencyconversionapi.model.CurrencyTransactionResponse;
import org.example.currencyconversionapi.service.CurrencyTransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CurrencyTransactionController.class)
public class CurrencyTransactionControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private CurrencyTransactionService currencyTransactionService;

    private Long userId = 1L;
    private String originCurrency = Currency.EUR.getValue();
    private BigDecimal originValue = BigDecimal.ONE;
    private String destinationCurrency = Currency.BRL.getValue();

    @Test
    public void makeConversionSuccessTest() {
        when(currencyTransactionService.makeConversion(userId, originCurrency, originValue, destinationCurrency))
                .thenReturn(Mono.just(new CurrencyTransaction()));

        webClient
                .get().uri(uriBuilder -> uriBuilder
                .path("/currency-transaction/conversion")
                .queryParam("userId", userId)
                .queryParam("originCurrency", originCurrency)
                .queryParam("originValue", originValue)
                .queryParam("destinationCurrency", destinationCurrency)
                .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CurrencyTransactionResponse.class)
                .value(currencyTransactionResponse -> {
                    assertEquals(HttpStatus.OK.value(), currencyTransactionResponse.getStatus());
                    assertNull(currencyTransactionResponse.getMessage());
                    assertNotNull(currencyTransactionResponse.getData());
                });
    }

    @Test
    public void makeConversionFailWithNoSuchCurrencyTest() {
        String messageException = "Invalid currency";
        when(currencyTransactionService.makeConversion(userId, originCurrency, originValue, destinationCurrency))
                .thenReturn(Mono.error(new NoSuchCurrencyException(messageException)));

        webClient
                .get().uri(uriBuilder -> uriBuilder
                .path("/currency-transaction/conversion")
                .queryParam("userId", userId)
                .queryParam("originCurrency", originCurrency)
                .queryParam("originValue", originValue)
                .queryParam("destinationCurrency", destinationCurrency)
                .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CurrencyTransactionResponse.class)
                .value(currencyTransactionResponse -> {
                    assertEquals(HttpStatus.BAD_REQUEST.value(), currencyTransactionResponse.getStatus());
                    assertEquals(messageException, currencyTransactionResponse.getMessage());
                    assertNull(currencyTransactionResponse.getData());
                });
    }

    @Test
    public void makeConversionFailOnConnectToExchangeRateApiTest() {
        String messageException = "Failed on connecting to ExchangeRateApi";
        when(currencyTransactionService.makeConversion(userId, originCurrency, originValue, destinationCurrency))
                .thenReturn(Mono.error(new ExchangeRateApiException(messageException)));

        webClient
                .get().uri(uriBuilder -> uriBuilder
                .path("/currency-transaction/conversion")
                .queryParam("userId", userId)
                .queryParam("originCurrency", originCurrency)
                .queryParam("originValue", originValue)
                .queryParam("destinationCurrency", destinationCurrency)
                .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(CurrencyTransactionResponse.class)
                .value(currencyTransactionResponse -> {
                    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), currencyTransactionResponse.getStatus());
                    assertEquals(messageException, currencyTransactionResponse.getMessage());
                    assertNull(currencyTransactionResponse.getData());
                });
    }

    @Test
    public void getAllCurrencyTransactionsSuccessTest() {
        when(currencyTransactionService.getAllCurrencyTransactions(userId))
                .thenReturn(Flux.just(new CurrencyTransaction()));

        webClient
                .get().uri(uriBuilder -> uriBuilder
                .path("/currency-transaction/user/{userId}")
                .build(userId))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(List.class);
    }
}
