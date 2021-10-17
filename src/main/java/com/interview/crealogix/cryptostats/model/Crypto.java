package com.interview.crealogix.cryptostats.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
