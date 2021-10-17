package com.interview.crealogix;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class CainMarketResponse {
    public Status status;
    public List<Crypto> data;
}

class Status {
    public Date timestamp;
    public int error_code;
    public Object error_message;
    public int elapsed;
    public int credit_count;
    public Object notice;
    public int total_count;
}

class USD {
    public double price;
    @JsonProperty("volume_24h")
    public double volume24h;
    @JsonProperty("market_cap")
    public double marketCap;
}

class Quote {
    @JsonProperty("USD")
    public USD uSD;
}

class Crypto {
    public String name;
    public String symbol;
    public Quote quote;
}

