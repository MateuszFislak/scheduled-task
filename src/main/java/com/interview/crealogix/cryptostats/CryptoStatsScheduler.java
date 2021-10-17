package com.interview.crealogix.cryptostats;

import com.interview.crealogix.cryptostats.model.CryptoSortField;
import com.interview.crealogix.cryptostats.model.CryptoSortOrder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CryptoStatsScheduler {

    private final CryptoStatsPrinter statsPrinter;

    public CryptoStatsScheduler(CryptoStatsPrinter statsPrinter) {
        this.statsPrinter = statsPrinter;
    }


    @Scheduled(fixedDelayString = "${stats.refresh.interval.seconds}", timeUnit = TimeUnit.SECONDS)
    public void scheduleCryptoStatsRefresh() {
        statsPrinter.printCryptoStats(CryptoSortField.MARKET_CAP, CryptoSortOrder.DESC);
    }
}
