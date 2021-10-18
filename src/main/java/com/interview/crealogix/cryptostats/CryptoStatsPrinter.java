package com.interview.crealogix.cryptostats;

import com.interview.crealogix.cryptostats.coinmarketclient.CoinMarketClient;
import com.interview.crealogix.cryptostats.cryptosorter.CryptoSortField;
import com.interview.crealogix.cryptostats.cryptosorter.CryptoSortOrder;
import com.interview.crealogix.cryptostats.cryptosorter.CryptoSorter;
import com.interview.crealogix.cryptostats.model.Crypto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoStatsPrinter {

    private final Logger logger;
    private final CoinMarketClient coinMarketClient;
    private final CryptoSorter cryptoSorter;


    public CryptoStatsPrinter(Logger logger, CoinMarketClient coinMarketClient, CryptoSorter cryptoSorter) {
        this.logger = logger;
        this.coinMarketClient = coinMarketClient;
        this.cryptoSorter = cryptoSorter;
    }

    public void printCryptoStats(CryptoSortField sortField, CryptoSortOrder sortOrder) {
        try {
            final List<Crypto> cryptos = coinMarketClient.getCryptoCurrencies();
            final List<Crypto> sorted = cryptoSorter.sort(cryptos, sortField, sortOrder, 10);
            printStatistics(sorted, sortField, sortOrder);
        } catch (Exception e) {
            printError(e);
        }
    }

    private void printError(Exception e) {
        System.out.println("\n\n");
        logger.error("! Could not fetch crypto currency list !", e);
    }

    private void printStatistics(List<Crypto> cryptos, CryptoSortField sortField, CryptoSortOrder sortOrder) {
        final String header = String.format("\n### Crypto Currencies List, Sorting: %s with Order: %s ###\n", sortField, sortOrder);
        final String stats = cryptos.stream()
                .map(crypto -> String.format("%s >> Price: %s | Volume(24h): %s | Market Cap: %s", crypto.getName(), crypto.getPrice(), crypto.getVolume24h(), crypto.getMarketCap()))
                .collect(Collectors.joining("\n", header, "\n"));
        logger.info(stats);
    }
}
