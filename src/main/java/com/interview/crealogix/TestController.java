package com.interview.crealogix;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class TestController {

    private final CoinMarketClient coinMarketCapClient;

    public TestController(CoinMarketClient coinMarketCapClient) {
        this.coinMarketCapClient = coinMarketCapClient;
    }

    @GetMapping
    public ResponseEntity<CainMarketResponse> getCryptos() {
        final CainMarketResponse response = coinMarketCapClient.getCurrencies(Optional.of(1), Optional.of(100), Optional.of("USD"));
        return ResponseEntity.ok(response);
    }
}
