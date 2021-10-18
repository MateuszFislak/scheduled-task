package com.interview.crealogix.cryptostats;

import com.interview.crealogix.cryptostats.coinmarketclient.CoinMarketClient;
import com.interview.crealogix.cryptostats.cryptosorter.CryptoSorter;
import com.interview.crealogix.cryptostats.model.Crypto;
import com.interview.crealogix.cryptostats.model.Quote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.interview.crealogix.cryptostats.cryptosorter.CryptoSortField.PRICE;
import static com.interview.crealogix.cryptostats.cryptosorter.CryptoSortOrder.DESC;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CryptoStatsPrinterTest {

    @Mock
    private Logger logger;

    @Mock
    private  CoinMarketClient coinMarketClient;

    @Mock
    private  CryptoSorter cryptoSorter;

    @InjectMocks
    private CryptoStatsPrinter cryptoStatsPrinter;

    @Test
    public void shouldPrintErrorMessageWhenExceptionThrown() {
        when(coinMarketClient.getCryptoCurrencies()).thenThrow(new RuntimeException("expected"));
        cryptoStatsPrinter.printCryptoStats(PRICE, DESC);
        verify(logger, times(1)).error(anyString(), any(Exception.class));
    }

    @Test
    public void shouldPrintCryptos() {
        final List<Crypto> cryptos = createCryptos();
        when(coinMarketClient.getCryptoCurrencies()).thenReturn(new ArrayList<>());
        when(cryptoSorter.sort(new ArrayList<>(), PRICE, DESC, 10)).thenReturn(cryptos);
        cryptoStatsPrinter.printCryptoStats(PRICE, DESC);
        verify(logger, times(1)).info(anyString());
    }

    private List<Crypto> createCryptos() {
        return IntStream.range(0, 10).mapToObj(i -> Crypto.builder().name(String.valueOf(i) + i + i)
                .symbol(String.valueOf(i))
                .quote(Quote.builder()
                        .marketCap(i)
                        .price(i * 10)
                        .volume24h(i * 100)
                        .currency(Currency.getInstance("USD"))
                        .build())
                .build()).collect(Collectors.toList());
    }
}