package org.example.currencyconversionapi.service;

import org.example.currencyconversionapi.enums.Currency;
import org.example.currencyconversionapi.model.exchangerateapi.ExchangeRateApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.function.Function;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ExchangeRateApiServiceTest {

    @Mock
    private WebClient webClient;

    @InjectMocks
    private ExchangeRateApiService exchangeRateApiService;

    @Test
    public void getConversionSuccessTest() {
        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(ExchangeRateApiResponse.class)).thenReturn(Mono.empty());

        exchangeRateApiService.getConversion(Currency.EUR);

        verify(webClient, times(1)).get();
        verify(requestHeadersUriSpec, times(1)).uri(any(Function.class));
        verify(requestHeadersUriSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(ExchangeRateApiResponse.class);
    }
}
