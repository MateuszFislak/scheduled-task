package com.interview.crealogix.cryptostats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private Currency currency;
    private double price;
    private double volume24h;
    private double marketCap;
}

