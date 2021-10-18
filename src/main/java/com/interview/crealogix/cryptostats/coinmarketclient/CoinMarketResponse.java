package com.interview.crealogix.cryptostats.coinmarketclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
class CoinMarketResponse {
    private Status statusResponse;
    private List<Crypto> data;

    @Data
   static class Status {
        private Date timestamp;
        private int error_code;
        private Object error_message;
        private int elapsed;
        private int credit_count;
        private Object notice;
        private int total_count;
    }

    @Data
    static class Currency {
        private double price;
        @JsonProperty("volume_24h")
        private double volume24h;
        @JsonProperty("market_cap")
        private double marketCap;
    }


    @Data
    static class Quote {
        @JsonProperty("USD")
        private Currency uSD;
    }

    @Data
    static class Crypto {
        private String name;
        private String symbol;
        private Quote quote;
    }
}








