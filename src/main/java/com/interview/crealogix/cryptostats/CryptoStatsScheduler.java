package com.interview.crealogix.cryptostats;

import com.interview.crealogix.cryptostats.cryptosorter.CryptoSortField;
import com.interview.crealogix.cryptostats.cryptosorter.CryptoSortOrder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CryptoStatsScheduler {

    private final CryptoStatsPrinter statsPrinter;

    public CryptoStatsScheduler(CryptoStatsPrinter statsPrinter) {
        this.statsPrinter = statsPrinter;
    }


    @Scheduled(fixedDelayString = "${stats.refresh.interval.miliseconds}")
    public void scheduleCryptoStatsRefresh() {
        statsPrinter.printCryptoStats(CryptoSortField.PRICE, CryptoSortOrder.DESC);
    }
}
