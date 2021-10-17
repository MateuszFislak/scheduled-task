package com.interview.crealogix.cryptostats.cryptosorter;

import com.interview.crealogix.cryptostats.model.Crypto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;

@Component
public class CryptoSorter {

    private final EnumMap<CryptoSortField, Comparator<Crypto>> sorters = new EnumMap<CryptoSortField, Comparator<Crypto>>(CryptoSortField.class) {{
        put(CryptoSortField.MARKET_CAP, comparingDouble(Crypto::getMarketCap));
        put(CryptoSortField.PRICE, comparingDouble(Crypto::getPrice));
        put(CryptoSortField.VOLUME_24H, comparingDouble(Crypto::getVolume24h));
    }};

    public List<Crypto> sort(List<Crypto> cryptos, CryptoSortField sortField, CryptoSortOrder sortOrder, Integer limit) {
        final Comparator<Crypto> comparator = prepareComparator(sortField, sortOrder);
        return cryptos.stream()
                .limit(limit)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private Comparator<Crypto> prepareComparator(CryptoSortField sortField, CryptoSortOrder cryptoSortOrder) {
        final Comparator<Crypto> comparator = sorters.get(sortField);
        if (CryptoSortOrder.DESC.equals(cryptoSortOrder)) {
            return comparator.reversed();
        }
        return comparator;
    }
}
