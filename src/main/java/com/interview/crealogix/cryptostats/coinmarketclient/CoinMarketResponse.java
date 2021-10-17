package com.interview.crealogix.cryptostats.coinmarketclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CoinMarketResponse {
    private Status status;
    private List<Crypto> data;
}

@Data
class Status {
    private Date timestamp;
    private int error_code;
    private Object error_message;
    private int elapsed;
    private int credit_count;
    private Object notice;
    private int total_count;
}

@Data
class USD {
    private double price;
    @JsonProperty("volume_24h")
    private double volume24h;
    @JsonProperty("market_cap")
    private double marketCap;
}

@Data
class Quote {
    @JsonProperty("USD")
    private USD uSD;
}

@Data
class Crypto {
    private String name;
    private String symbol;
    private Quote quote;
}

