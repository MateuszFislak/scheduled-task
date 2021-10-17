package com.interview.crealogix.cryptostats.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Crypto {
    private String name;
    private String symbol;
    private Quote quote;
}
