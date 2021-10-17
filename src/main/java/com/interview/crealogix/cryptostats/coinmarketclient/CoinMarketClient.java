package com.interview.crealogix.cryptostats.coinmarketclient;

import com.interview.crealogix.cryptostats.model.Crypto;
import com.interview.crealogix.cryptostats.model.CryptoSortField;
import com.interview.crealogix.cryptostats.model.CryptoSortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CoinMarketClient {

    public static final String AUTHENTICATION_HEADER = "X-CMC_PRO_API_KEY";
    private final String apiKey;
    private final String currencyUrl;
    private final WebClient webClient;
    private final ResponseFormatter responseFormatter;

    public CoinMarketClient(@Value("${coinmarket.api.key}") String apiKey,
                            @Value("${coinmarket.cryptocurrency.url}") String currencyUrl,
                            WebClient webClient,
                            ResponseFormatter responseFormatter) {
        this.apiKey = apiKey;
        this.currencyUrl = currencyUrl;
        this.webClient = webClient;
        this.responseFormatter = responseFormatter;
    }

    public List<Crypto> getCryptoCurrencies(CryptoSortField sortField, CryptoSortOrder cryptoSortOrder) {
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
                .map(responseFormatter::format)
                .block();
    }
}
