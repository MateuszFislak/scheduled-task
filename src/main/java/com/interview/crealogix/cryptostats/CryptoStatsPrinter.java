package com.interview.crealogix.cryptostats;

import com.interview.crealogix.cryptostats.coinmarketclient.CoinMarketClient;
import com.interview.crealogix.cryptostats.coinmarketclient.CoinMarketResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CryptoStatsPrinter {

    Logger logger = LoggerFactory.getLogger(CryptoStatsPrinter.class);
    private final CoinMarketClient coinMarketClient;


    public CryptoStatsPrinter(CoinMarketClient coinMarketClient) {
        this.coinMarketClient = coinMarketClient;
    }

    public void printCryptoStats(CryptoSortField sortField, CryptoSortOrder sortOrder) {
        final CoinMarketResponse cryptoCurrencies = coinMarketClient.getCryptoCurrencies(sortField, sortOrder);
        logger.info(String.format("### Crypto Currencies List, Sorting: %s with Order: %s ###", sortField, sortOrder));
//        cryptoCurrencies.getData().forEach(crypto -> {
//            logger.info(String.format("Name: %s | Price: %s | Volume(24h): %s | Market Cap: %s", crypto.getName()));
//        });
    }


}
