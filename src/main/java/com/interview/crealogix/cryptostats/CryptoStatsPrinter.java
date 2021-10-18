package com.interview.crealogix.cryptostats;

import com.interview.crealogix.cryptostats.coinmarketclient.CoinMarketClient;
import com.interview.crealogix.cryptostats.cryptosorter.CryptoSorter;
import com.interview.crealogix.cryptostats.model.Crypto;
import com.interview.crealogix.cryptostats.cryptosorter.CryptoSortField;
import com.interview.crealogix.cryptostats.cryptosorter.CryptoSortOrder;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

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
            printHeader(sortField, sortOrder);
            printStatistics(sorted);
        } catch (Exception e) {
            printError(e);
        }
    }

    private void printError(Exception e) {
        System.out.println("\n\n");
        logger.error("! Could not fetch crypto currency list !", e);
    }

    private void printStatistics(List<Crypto> cryptos) {
        cryptos.stream()
                .map(crypto -> String.format("%s >> Price: %s | Volume(24h): %s | Market Cap: %s", crypto.getName(), crypto.getPrice(), crypto.getVolume24h(), crypto.getMarketCap()))
                .forEach(logger::info);
    }

    private void printHeader(CryptoSortField sortField, CryptoSortOrder sortOrder) {
        System.out.println("\n\n");
        logger.info(String.format("### Crypto Currencies List, Sorting: %s with Order: %s ###", sortField, sortOrder));
    }


}
