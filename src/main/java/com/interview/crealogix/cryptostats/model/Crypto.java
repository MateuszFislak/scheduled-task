package com.interview.crealogix.cryptostats.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Crypto {
    private String name;
    private String symbol;
    private Quote quote;

    public double getPrice() {
        return getQuote().getPrice();
    }

    public double getVolume24h() {
        return getQuote().getVolume24h();
    }

    public double getMarketCap() {
        return getQuote().getMarketCap();
    }
}
