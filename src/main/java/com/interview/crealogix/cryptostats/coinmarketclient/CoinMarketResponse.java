package com.interview.crealogix.cryptostats.coinmarketclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
class CoinMarketResponse {
    private StatusResponse statusResponse;
    private List<CryptoResponse> data;
}

@Data
class StatusResponse {
    private Date timestamp;
    private int error_code;
    private Object error_message;
    private int elapsed;
    private int credit_count;
    private Object notice;
    private int total_count;
}

@Data
class CurrencyResponse {
    private double price;
    @JsonProperty("volume_24h")
    private double volume24h;
    @JsonProperty("market_cap")
    private double marketCap;
}

@Data
class QuoteResponse {
    @JsonProperty("USD")
    private CurrencyResponse uSD;
}

@Data
class CryptoResponse {
    private String name;
    private String symbol;
    private QuoteResponse quote;
}

