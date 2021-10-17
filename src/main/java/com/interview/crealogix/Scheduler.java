package com.interview.crealogix;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class Scheduler {

    private final CoinMarketClient coinMarketClient;

    public Scheduler(CoinMarketClient coinMarketClient) {
        this.coinMarketClient = coinMarketClient;
    }


    @Scheduled(fixedDelayString = "${query.interval.seconds}", timeUnit = TimeUnit.SECONDS)
    public void readCryptos() {
        System.out.println("hello world");
    }
}
