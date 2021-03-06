package com.interview.crealogix.cryptostats.coinmarketclient;


import com.interview.crealogix.cryptostats.model.Crypto;
import com.interview.crealogix.cryptostats.model.Quote;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Component
class ResponseMapper {

    final public ModelMapper mapper;

    ResponseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    List<Crypto> mapToCrypto(CoinMarketResponse response) {
        return response.getData().stream().map(this::mapResponseToCrypto).collect(Collectors.toList());
    }

    private Crypto mapResponseToCrypto(CoinMarketResponse.Crypto cryptoResponse) {
        Quote quote = mapper.map(cryptoResponse.getQuote().getUSD(), Quote.class);
        quote.setCurrency(Currency.getInstance("USD"));
        Crypto crypto = mapper.map(cryptoResponse, Crypto.class);
        crypto.setQuote(quote);
        return crypto;
    }
}
