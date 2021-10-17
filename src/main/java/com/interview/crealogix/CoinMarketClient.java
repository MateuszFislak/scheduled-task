package com.interview.crealogix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class CoinMarketClient {

    public static final String AUTHENTICATION_HEADER = "X-CMC_PRO_API_KEY";
    private final String apiKey;
    private final String currencyUrl;
    private final WebClient webClient;

    public CoinMarketClient(@Value("${api.key}") String apiKey,
                            @Value("${coinmarketcap.cryptocurrency.url}") String currencyUrl,
                            WebClient webClient) {
        this.apiKey = apiKey;
        this.currencyUrl = currencyUrl;
        this.webClient = webClient;
    }

    public CainMarketResponse getCurrencies(Optional<Integer> start, Optional<Integer> limit, Optional<String> currency) {
        return webClient.get()
                .uri(currencyUrl, uriBuilder -> uriBuilder
                        .queryParamIfPresent("start", start)
                        .queryParamIfPresent("limit", limit)
                        .queryParamIfPresent("convert", currency)
                        .build()
                )
                .header(AUTHENTICATION_HEADER, apiKey)
                .retrieve()
                .bodyToMono(CainMarketResponse.class)
                .block();
    }
}
