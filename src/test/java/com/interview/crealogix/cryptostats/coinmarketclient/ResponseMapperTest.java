package com.interview.crealogix.cryptostats.coinmarketclient;

import com.interview.crealogix.cryptostats.model.Crypto;
import com.interview.crealogix.cryptostats.model.Quote;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Currency;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseMapperTest {

    @Test
    public void shouldMapResponseObjectToCrypto() {
        final ResponseMapper responseMapper = new ResponseMapper(new ModelMapper());

        CoinMarketResponse response = createCoinMarketResponse();
        List<Crypto> expectedCryptos = createExpected();

        final List<Crypto> result = responseMapper.mapToCrypto(response);
        assertThat(result).isEqualTo(expectedCryptos);
    }

    private List<Crypto> createExpected() {
        return IntStream.range(0, 5).mapToObj(i -> Crypto.builder().name(String.valueOf(i) + i + i)
                .symbol(String.valueOf(i))
                .quote(Quote.builder()
                        .marketCap(i)
                        .price(i * 10)
                        .volume24h(i * 100)
                        .currency(Currency.getInstance("USD"))
                        .build())
                .build()).collect(Collectors.toList());
    }

    private CoinMarketResponse createCoinMarketResponse() {
        CoinMarketResponse response = new CoinMarketResponse();
        final List<CoinMarketResponse.Crypto> cryptos = IntStream.range(0, 5).mapToObj(i -> {
            final CoinMarketResponse.Crypto crypto = new CoinMarketResponse.Crypto();
            crypto.setName(String.valueOf(i) + i + i);
            crypto.setSymbol(String.valueOf(i));
            CoinMarketResponse.Quote quote = new CoinMarketResponse.Quote();
            CoinMarketResponse.Currency currency = new CoinMarketResponse.Currency();
            currency.setMarketCap(i);
            currency.setPrice(i * 10);
            currency.setVolume24h(i * 100);
            quote.setUSD(currency);
            crypto.setQuote(quote);
            return crypto;
        }).collect(Collectors.toList());
        response.setData(cryptos);
        return response;
    }
}