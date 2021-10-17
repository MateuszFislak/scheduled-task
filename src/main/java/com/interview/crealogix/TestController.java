package com.interview.crealogix;

import com.interview.crealogix.cryptostats.coinmarketclient.CoinMarketResponse;
import com.interview.crealogix.cryptostats.CryptoSortField;
import com.interview.crealogix.cryptostats.CryptoSortOrder;
import com.interview.crealogix.cryptostats.CryptoStatsPrinter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private final CryptoStatsPrinter cryptoStatsPrinter;

    public TestController(CryptoStatsPrinter cryptoStatsPrinter) {
        this.cryptoStatsPrinter = cryptoStatsPrinter;
    }


    @GetMapping
    public ResponseEntity<CoinMarketResponse> getCryptos() {
        cryptoStatsPrinter.printCryptoStats(CryptoSortField.MARKET_CAP, CryptoSortOrder.DESC);
        return ResponseEntity.ok().build();
    }
}
