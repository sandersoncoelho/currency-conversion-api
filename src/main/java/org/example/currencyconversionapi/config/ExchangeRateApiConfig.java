package org.example.currencyconversionapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.AbstractMap;
import java.util.Map;

@Configuration
public class ExchangeRateApiConfig {

    @Value("${exchangeRateApi.url}")
    private String url;

    @Value("${exchangeRateApi.path}")
    private String path;

    @Value("${exchangeRateApi.accessKey}")
    private String accessKey;

    @Value("${exchangeRateApi.format}")
    private String format;

    @Bean
    public WebClient getWebClient() {
        Map<String, String> defaultQueryParams = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("access_key", accessKey),
                new AbstractMap.SimpleEntry<>("format", format));

        return WebClient.builder()
                .baseUrl(url + path)
                .defaultUriVariables(defaultQueryParams)
                .build();
    }
}
