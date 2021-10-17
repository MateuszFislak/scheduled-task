package com.interview.crealogix.cryptostats.coinmarketclient;

import com.interview.crealogix.cryptostats.CryptoSortField;
import com.interview.crealogix.cryptostats.CryptoSortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CoinMarketClient {

    public static final String AUTHENTICATION_HEADER = "X-CMC_PRO_API_KEY";
    private final String apiKey;
    private final String currencyUrl;
    private final WebClient webClient;

    public CoinMarketClient(@Value("${coinmarket.api.key}") String apiKey,
                            @Value("${coinmarket.cryptocurrency.url}") String currencyUrl,
                            WebClient webClient) {
        this.apiKey = apiKey;
        this.currencyUrl = currencyUrl;
        this.webClient = webClient;
    }

    public CoinMarketResponse getCryptoCurrencies(CryptoSortField sortField, CryptoSortOrder cryptoSortOrder) {
        return webClient.get()
                .uri(currencyUrl, uriBuilder -> uriBuilder
                        .queryParam("start", 1)
                        .queryParam("limit", 10)
                        .queryParam("convert", "USD")
                        .queryParam("sort", sortField.name().toLowerCase())
                        .queryParam("sort_dir", cryptoSortOrder.name().toLowerCase())
                        .build()
                )
                .header(AUTHENTICATION_HEADER, apiKey)
                .retrieve()
                .bodyToMono(CoinMarketResponse.class)
                .map(coinMarketResponse -> coinMarketResponse)
                .block();
    }
}
