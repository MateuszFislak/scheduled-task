package com.interview.crealogix.cryptostats.model;

public enum CryptoSortField {
    MARKET_CAP("Market Cap"), PRICE("Price"), VOLUME_24H("Volume(24h)");

    public final String label;

    CryptoSortField(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
