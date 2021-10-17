package com.interview.crealogix.cryptostats;

import com.interview.crealogix.cryptostats.coinmarketclient.CoinMarketClient;
import com.interview.crealogix.cryptostats.model.Crypto;
import com.interview.crealogix.cryptostats.model.CryptoSortField;
import com.interview.crealogix.cryptostats.model.CryptoSortOrder;
import com.interview.crealogix.cryptostats.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoStatsPrinter {

    private final Logger logger = LoggerFactory.getLogger(CryptoStatsPrinter.class);
    private final CoinMarketClient coinMarketClient;


    public CryptoStatsPrinter(CoinMarketClient coinMarketClient) {
        this.coinMarketClient = coinMarketClient;
    }

    public void printCryptoStats(CryptoSortField sortField, CryptoSortOrder sortOrder) {
        try {
            final List<Crypto> cryptos = coinMarketClient.getCryptoCurrencies(sortField, sortOrder);

            printHeader(sortField, sortOrder);
            printStatistics(cryptos);
        } catch (Exception e) {
            printError();
        }
    }

    private void printError() {
        System.out.println("\n\n");
        logger.error("! Could not fetch crypto currency list !");
    }

    private void printStatistics(List<Crypto> cryptos) {
        cryptos.forEach(crypto -> {
            final Quote quote = crypto.getQuote();
            logger.info(String.format("%s >> Price: %s | Volume(24h): %s | Market Cap: %s", crypto.getName(), quote.getPrice(), quote.getVolume24h(), quote.getMarketCap()));
        });
    }

    private void printHeader(CryptoSortField sortField, CryptoSortOrder sortOrder) {
        System.out.println("\n\n");
        logger.info(String.format("### Crypto Currencies List, Sorting: %s with Order: %s ###", sortField, sortOrder));
    }


}
