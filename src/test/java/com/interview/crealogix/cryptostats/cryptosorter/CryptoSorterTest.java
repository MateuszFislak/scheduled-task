package com.interview.crealogix.cryptostats.cryptosorter;

import com.interview.crealogix.cryptostats.model.Crypto;
import com.interview.crealogix.cryptostats.model.Quote;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


class CryptoSorterTest {

    final CryptoSorter cryptoSorter = new CryptoSorter();
    final Random random = new Random();

    public List<Crypto> testData = IntStream.range(0, 10).mapToObj(i -> Crypto.builder()
            .name(String.valueOf(i))
            .quote(Quote.builder().price(random.nextDouble()).marketCap(random.nextDouble()).volume24h(random.nextDouble()).build())
            .build()
    ).collect(Collectors.toList());

    @Test
    public void shouldLimitSortTo3() {
        final List<Crypto> sorted = cryptoSorter.sort(testData, CryptoSortField.PRICE, CryptoSortOrder.ASC, 3);
        assertThat(sorted.size()).isEqualTo(3);
        assertThat(sorted).map(Crypto::getName).containsOnly("0", "1", "2");
    }

    @Test
    public void shouldSortByPriceAscending() {
        final List<Crypto> sorted = cryptoSorter.sort(testData, CryptoSortField.PRICE, CryptoSortOrder.ASC, 5);
        assertThat(sorted.size()).isEqualTo(5);
        assertThat(sorted).map(Crypto::getName).containsOnly("0", "1", "2", "3", "4");
        assertThat(sorted).map(Crypto::getPrice).isSorted();
    }

    @Test
    public void shouldSortByPriceDescending() {
        final List<Crypto> sorted = cryptoSorter.sort(testData, CryptoSortField.PRICE, CryptoSortOrder.DESC, 5);
        assertThat(sorted.size()).isEqualTo(5);
        assertThat(sorted).map(Crypto::getName).containsOnly("0", "1", "2", "3", "4");
        assertThat(sorted).map(Crypto::getPrice).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    public void shouldSortByMarketAscending() {
        final List<Crypto> sorted = cryptoSorter.sort(testData, CryptoSortField.MARKET_CAP, CryptoSortOrder.DESC, 5);
        assertThat(sorted.size()).isEqualTo(5);
        assertThat(sorted).map(Crypto::getName).containsOnly("0", "1", "2", "3", "4");
        assertThat(sorted).map(Crypto::getMarketCap).isSortedAccordingTo(Comparator.reverseOrder());
    }
    @Test
    public void shouldSortByMarketDescending() {
        final List<Crypto> sorted = cryptoSorter.sort(testData, CryptoSortField.MARKET_CAP, CryptoSortOrder.DESC, 5);
        assertThat(sorted.size()).isEqualTo(5);
        assertThat(sorted).map(Crypto::getName).containsOnly("0", "1", "2", "3", "4");
        assertThat(sorted).map(Crypto::getMarketCap).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    public void shouldSortByVolumeAscending() {
        final List<Crypto> sorted = cryptoSorter.sort(testData, CryptoSortField.VOLUME_24H, CryptoSortOrder.DESC, 5);
        assertThat(sorted.size()).isEqualTo(5);
        assertThat(sorted).map(Crypto::getName).containsOnly("0", "1", "2", "3", "4");
        assertThat(sorted).map(Crypto::getVolume24h).isSortedAccordingTo(Comparator.reverseOrder());
    }
    @Test
    public void shouldSortByVolumeDescending() {
        final List<Crypto> sorted = cryptoSorter.sort(testData, CryptoSortField.VOLUME_24H, CryptoSortOrder.DESC, 5);
        assertThat(sorted.size()).isEqualTo(5);
        assertThat(sorted).map(Crypto::getName).containsOnly("0", "1", "2", "3", "4");
        assertThat(sorted).map(Crypto::getVolume24h).isSortedAccordingTo(Comparator.reverseOrder());
    }


}