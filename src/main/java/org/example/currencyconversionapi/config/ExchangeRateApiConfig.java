package org.example.currencyconversionapi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ExchangeRateApiConfig {

    @Value("${exchangeRateApi.url}")
    private String url;

    @Value("${exchangeRateApi.path}")
    private String path;

    @Value("${exchangeRateApi.accessKey}")
    private String accessKey;

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(url + path)
                .build();
    }

    @Bean
    @Qualifier("exchangeRateApiDefaultQueryParams")
    public MultiValueMap<String, String> getDefaultQueryParams() {
        MultiValueMap<String, String> defaultQueryParams = new LinkedMultiValueMap<>();
        defaultQueryParams.add("access_key", accessKey);
        return defaultQueryParams;
    }
}
