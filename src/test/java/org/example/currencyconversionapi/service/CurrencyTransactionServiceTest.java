package org.example.currencyconversionapi.service;

import org.example.currencyconversionapi.enums.Currency;
import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.example.currencyconversionapi.model.exchangerateapi.ExchangeRateApiResponse;
import org.example.currencyconversionapi.repository.CurrencyTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CurrencyTransactionServiceTest {

    @Mock
    private ExchangeRateApiService exchangeRateApiService;

    @Mock
    private CurrencyTransactionRepository currencyTransactionRepository;

    @InjectMocks
    private CurrencyTransactionService currencyTransactionService;

    private Long currencyTransactionId = 1L;
    private Long userId = 1L;
    private String originCurrency = Currency.EUR.getValue();
    private BigDecimal originValue = BigDecimal.ONE;
    private String destinationCurrency = Currency.BRL.getValue();
    private BigDecimal conversionRate = BigDecimal.valueOf(12.12);
    private Instant now = Instant.now();

    @Captor
    private ArgumentCaptor<CurrencyTransaction> currencyTransactionCaptor;

    @Test
    public void makeConversionSuccessTest() {
        ExchangeRateApiResponse exchangeRateApiResponse = createExchangeRateApiResponse();
        CurrencyTransaction currencyTransaction = createCurrencyTransaction();

        when(exchangeRateApiService.getConversion(Currency.EUR)).thenReturn(Mono.just(exchangeRateApiResponse));
        when(currencyTransactionRepository.save(any())).thenReturn(currencyTransaction);

        Mono<CurrencyTransaction> currencyTransactionMono = currencyTransactionService
                .makeConversion(userId, originCurrency, originValue, destinationCurrency);

        StepVerifier
                .create(currencyTransactionMono)
                .consumeNextWith(transaction -> {
                    assertEquals(transaction.getCurrencyTransactionId(), currencyTransactionId);
                    assertEquals(transaction.getUserId(), userId);
                    assertEquals(transaction.getOriginCurrency(), originCurrency);
                    assertEquals(transaction.getOriginValue(), originValue);
                    assertEquals(transaction.getDestinationCurrency(), destinationCurrency);
                    assertEquals(transaction.getConversionRate(), conversionRate);
                    assertEquals(transaction.getDateTimeTransaction(), now);
                })
                .verifyComplete();

        verify(currencyTransactionRepository).save(currencyTransactionCaptor.capture());
        assertEquals(currencyTransactionCaptor.getValue().getDestinationValue(), conversionRate.multiply(originValue));
    }

    private CurrencyTransaction createCurrencyTransaction() {
        CurrencyTransaction currencyTransaction = new CurrencyTransaction();
        currencyTransaction.setCurrencyTransactionId(currencyTransactionId);
        currencyTransaction.setUserId(userId);
        currencyTransaction.setOriginCurrency(originCurrency);
        currencyTransaction.setOriginValue(originValue);
        currencyTransaction.setDestinationCurrency(destinationCurrency);
        currencyTransaction.setConversionRate(conversionRate);
        currencyTransaction.setDateTimeTransaction(now);
        return currencyTransaction;
    }

    private ExchangeRateApiResponse createExchangeRateApiResponse() {
        ExchangeRateApiResponse exchangeRateApiResponse = new ExchangeRateApiResponse();
        exchangeRateApiResponse.setRates(Map.of(destinationCurrency, conversionRate));
        return exchangeRateApiResponse;
    }

    @Test
    public void getAllCurrencyTransactionsSuccessTest() {
        when(currencyTransactionRepository.findByUserId(userId)).thenReturn(List.of(createCurrencyTransaction()));

        Flux<CurrencyTransaction> transactionFlux = currencyTransactionService.getAllCurrencyTransactions(userId);

        StepVerifier
                .create(transactionFlux)
                .consumeNextWith(transaction -> {
                    assertEquals(transaction.getCurrencyTransactionId(), currencyTransactionId);
                    assertEquals(transaction.getUserId(), userId);
                    assertEquals(transaction.getOriginCurrency(), originCurrency);
                    assertEquals(transaction.getOriginValue(), originValue);
                    assertEquals(transaction.getDestinationCurrency(), destinationCurrency);
                    assertEquals(transaction.getConversionRate(), conversionRate);
                    assertEquals(transaction.getDateTimeTransaction(), now);
                })
                .verifyComplete();
    }
}
