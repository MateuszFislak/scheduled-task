package com.interview.crealogix.cryptostats;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CryptoStatsScheduler {

    private final CryptoStatsPrinter cryptoStatsService;

    public CryptoStatsScheduler(CryptoStatsPrinter cryptoStatsService) {
        this.cryptoStatsService = cryptoStatsService;
    }


    @Scheduled(fixedDelayString = "${query.interval.seconds}", timeUnit = TimeUnit.SECONDS)
    public void readCryptos() {
        cryptoStatsService.printCryptoStats(CryptoSortField.MARKET_CAP, CryptoSortOrder.DESC);
    }
}
