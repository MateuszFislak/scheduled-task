package com.interview.crealogix.cryptostats;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.interview.crealogix.initializer.WireMockInitializer;
import org.awaitility.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ContextConfiguration(
        initializers = {WireMockInitializer.class}
)
@TestPropertySource(
        properties = {"stats.refresh.interval.miliseconds=200"}
)
@ActiveProfiles("test")
public class CryptoStatsIntegrationTest {

    @Autowired
    private WireMockServer wireMockServer;

    @MockBean
    private Logger logger;

    @BeforeEach
    void beforeAll() {
        wireMockServer.resetAll();
    }

    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {
        givenCoinMarketCurrenciesAreFetched();

        await().atMost(Duration.FIVE_SECONDS).until(() -> mockingDetails(logger).getInvocations().size() == 1);
        await().timeout(Duration.TWO_SECONDS)
                .untilAsserted(() -> verify(logger, atLeast(9)).info(anyString()));
    }

    private void givenCoinMarketCurrenciesAreFetched() {
        wireMockServer.stubFor(
                get(urlPathEqualTo("/v1/cryptocurrency/listings/latest"))
                        .willReturn(
                                WireMock.okJson(
                                        "{\n" +
                                                "    \"status\": {\n" +
                                                "        \"timestamp\": \"2021-10-18T18:52:07.910Z\",\n" +
                                                "        \"error_code\": 0,\n" +
                                                "        \"error_message\": null,\n" +
                                                "        \"elapsed\": 13,\n" +
                                                "        \"credit_count\": 1,\n" +
                                                "        \"notice\": null,\n" +
                                                "        \"total_count\": 6605\n" +
                                                "    },\n" +
                                                "    \"data\": [\n" +
                                                "        {\n" +
                                                "            \"id\": 1,\n" +
                                                "            \"name\": \"Bitcoin\",\n" +
                                                "            \"symbol\": \"BTC\",\n" +
                                                "            \"slug\": \"bitcoin\",\n" +
                                                "            \"num_market_pairs\": 8355,\n" +
                                                "            \"date_added\": \"2013-04-28T00:00:00.000Z\",\n" +
                                                "            \"tags\": [\n" +
                                                "                \"mineable\",\n" +
                                                "                \"pow\",\n" +
                                                "                \"sha-256\",\n" +
                                                "                \"store-of-value\",\n" +
                                                "                \"state-channels\",\n" +
                                                "                \"coinbase-ventures-portfolio\",\n" +
                                                "                \"three-arrows-capital-portfolio\",\n" +
                                                "                \"polychain-capital-portfolio\",\n" +
                                                "                \"binance-labs-portfolio\",\n" +
                                                "                \"arrington-xrp-capital\",\n" +
                                                "                \"blockchain-capital-portfolio\",\n" +
                                                "                \"boostvc-portfolio\",\n" +
                                                "                \"cms-holdings-portfolio\",\n" +
                                                "                \"dcg-portfolio\",\n" +
                                                "                \"dragonfly-capital-portfolio\",\n" +
                                                "                \"electric-capital-portfolio\",\n" +
                                                "                \"fabric-ventures-portfolio\",\n" +
                                                "                \"framework-ventures\",\n" +
                                                "                \"galaxy-digital-portfolio\",\n" +
                                                "                \"huobi-capital\",\n" +
                                                "                \"alameda-research-portfolio\",\n" +
                                                "                \"a16z-portfolio\",\n" +
                                                "                \"1confirmation-portfolio\",\n" +
                                                "                \"winklevoss-capital\",\n" +
                                                "                \"usv-portfolio\",\n" +
                                                "                \"placeholder-ventures-portfolio\",\n" +
                                                "                \"pantera-capital-portfolio\",\n" +
                                                "                \"multicoin-capital-portfolio\",\n" +
                                                "                \"paradigm-xzy-screener\"\n" +
                                                "            ],\n" +
                                                "            \"max_supply\": 21000000,\n" +
                                                "            \"circulating_supply\": 18847331,\n" +
                                                "            \"total_supply\": 18847331,\n" +
                                                "            \"platform\": null,\n" +
                                                "            \"cmc_rank\": 1,\n" +
                                                "            \"last_updated\": \"2021-10-18T18:51:02.000Z\",\n" +
                                                "            \"quote\": {\n" +
                                                "                \"USD\": {\n" +
                                                "                    \"price\": 61829.42616686577,\n" +
                                                "                    \"volume_24h\": 39624460982.74576,\n" +
                                                "                    \"volume_change_24h\": 54.9446,\n" +
                                                "                    \"percent_change_1h\": -0.33378991,\n" +
                                                "                    \"percent_change_24h\": 1.61282187,\n" +
                                                "                    \"percent_change_7d\": 7.67826598,\n" +
                                                "                    \"percent_change_30d\": 28.11378656,\n" +
                                                "                    \"percent_change_60d\": 32.8632294,\n" +
                                                "                    \"percent_change_90d\": 107.48548952,\n" +
                                                "                    \"market_cap\": 1165319660506.9805,\n" +
                                                "                    \"market_cap_dominance\": 47.1326,\n" +
                                                "                    \"fully_diluted_market_cap\": 1298417949504.18,\n" +
                                                "                    \"last_updated\": \"2021-10-18T18:51:02.000Z\"\n" +
                                                "                }\n" +
                                                "            }\n" +
                                                "        },\n" +
                                                "        {\n" +
                                                "            \"id\": 1027,\n" +
                                                "            \"name\": \"Ethereum\",\n" +
                                                "            \"symbol\": \"ETH\",\n" +
                                                "            \"slug\": \"ethereum\",\n" +
                                                "            \"num_market_pairs\": 4933,\n" +
                                                "            \"date_added\": \"2015-08-07T00:00:00.000Z\",\n" +
                                                "            \"tags\": [\n" +
                                                "                \"mineable\",\n" +
                                                "                \"pow\",\n" +
                                                "                \"smart-contracts\",\n" +
                                                "                \"ethereum\",\n" +
                                                "                \"binance-smart-chain\",\n" +
                                                "                \"coinbase-ventures-portfolio\",\n" +
                                                "                \"three-arrows-capital-portfolio\",\n" +
                                                "                \"polychain-capital-portfolio\",\n" +
                                                "                \"binance-labs-portfolio\",\n" +
                                                "                \"arrington-xrp-capital\",\n" +
                                                "                \"blockchain-capital-portfolio\",\n" +
                                                "                \"boostvc-portfolio\",\n" +
                                                "                \"cms-holdings-portfolio\",\n" +
                                                "                \"dcg-portfolio\",\n" +
                                                "                \"dragonfly-capital-portfolio\",\n" +
                                                "                \"electric-capital-portfolio\",\n" +
                                                "                \"fabric-ventures-portfolio\",\n" +
                                                "                \"framework-ventures\",\n" +
                                                "                \"hashkey-capital-portfolio\",\n" +
                                                "                \"kinetic-capital\",\n" +
                                                "                \"huobi-capital\",\n" +
                                                "                \"alameda-research-portfolio\",\n" +
                                                "                \"a16z-portfolio\",\n" +
                                                "                \"1confirmation-portfolio\",\n" +
                                                "                \"winklevoss-capital\",\n" +
                                                "                \"usv-portfolio\",\n" +
                                                "                \"placeholder-ventures-portfolio\",\n" +
                                                "                \"pantera-capital-portfolio\",\n" +
                                                "                \"multicoin-capital-portfolio\",\n" +
                                                "                \"paradigm-xzy-screener\"\n" +
                                                "            ],\n" +
                                                "            \"max_supply\": null,\n" +
                                                "            \"circulating_supply\": 117978860.5615,\n" +
                                                "            \"total_supply\": 117978860.5615,\n" +
                                                "            \"platform\": null,\n" +
                                                "            \"cmc_rank\": 2,\n" +
                                                "            \"last_updated\": \"2021-10-18T18:51:02.000Z\",\n" +
                                                "            \"quote\": {\n" +
                                                "                \"USD\": {\n" +
                                                "                    \"price\": 3732.840138765618,\n" +
                                                "                    \"volume_24h\": 18902832627.74962,\n" +
                                                "                    \"volume_change_24h\": 39.451,\n" +
                                                "                    \"percent_change_1h\": -0.24227453,\n" +
                                                "                    \"percent_change_24h\": -1.88264361,\n" +
                                                "                    \"percent_change_7d\": 4.97631271,\n" +
                                                "                    \"percent_change_30d\": 8.40291083,\n" +
                                                "                    \"percent_change_60d\": 19.57951054,\n" +
                                                "                    \"percent_change_90d\": 108.22486324,\n" +
                                                "                    \"market_cap\": 440396226229.79913,\n" +
                                                "                    \"market_cap_dominance\": 17.8123,\n" +
                                                "                    \"fully_diluted_market_cap\": 440396226229.8,\n" +
                                                "                    \"last_updated\": \"2021-10-18T18:51:02.000Z\"\n" +
                                                "                }\n" +
                                                "            }\n" +
                                                "        },\n" +
                                                "        {\n" +
                                                "            \"id\": 1839,\n" +
                                                "            \"name\": \"Binance Coin\",\n" +
                                                "            \"symbol\": \"BNB\",\n" +
                                                "            \"slug\": \"binance-coin\",\n" +
                                                "            \"num_market_pairs\": 537,\n" +
                                                "            \"date_added\": \"2017-07-25T00:00:00.000Z\",\n" +
                                                "            \"tags\": [\n" +
                                                "                \"marketplace\",\n" +
                                                "                \"centralized-exchange\",\n" +
                                                "                \"payments\",\n" +
                                                "                \"binance-smart-chain\",\n" +
                                                "                \"alameda-research-portfolio\",\n" +
                                                "                \"multicoin-capital-portfolio\"\n" +
                                                "            ],\n" +
                                                "            \"max_supply\": 168137036,\n" +
                                                "            \"circulating_supply\": 166801148,\n" +
                                                "            \"total_supply\": 166801148,\n" +
                                                "            \"platform\": null,\n" +
                                                "            \"cmc_rank\": 3,\n" +
                                                "            \"last_updated\": \"2021-10-18T18:50:08.000Z\",\n" +
                                                "            \"quote\": {\n" +
                                                "                \"USD\": {\n" +
                                                "                    \"price\": 480.2175209123789,\n" +
                                                "                    \"volume_24h\": 2119505152.2149367,\n" +
                                                "                    \"volume_change_24h\": 46.443,\n" +
                                                "                    \"percent_change_1h\": 0.33921738,\n" +
                                                "                    \"percent_change_24h\": 2.94821264,\n" +
                                                "                    \"percent_change_7d\": 16.39715346,\n" +
                                                "                    \"percent_change_30d\": 16.46065745,\n" +
                                                "                    \"percent_change_60d\": 12.69880338,\n" +
                                                "                    \"percent_change_90d\": 79.20754474,\n" +
                                                "                    \"market_cap\": 80100833777.8988,\n" +
                                                "                    \"market_cap_dominance\": 3.2398,\n" +
                                                "                    \"fully_diluted_market_cap\": 80742350601.48,\n" +
                                                "                    \"last_updated\": \"2021-10-18T18:50:08.000Z\"\n" +
                                                "                }\n" +
                                                "            }\n" +
                                                "        },\n" +
                                                "        {\n" +
                                                "            \"id\": 2010,\n" +
                                                "            \"name\": \"Cardano\",\n" +
                                                "            \"symbol\": \"ADA\",\n" +
                                                "            \"slug\": \"cardano\",\n" +
                                                "            \"num_market_pairs\": 306,\n" +
                                                "            \"date_added\": \"2017-10-01T00:00:00.000Z\",\n" +
                                                "            \"tags\": [\n" +
                                                "                \"mineable\",\n" +
                                                "                \"dpos\",\n" +
                                                "                \"pos\",\n" +
                                                "                \"platform\",\n" +
                                                "                \"research\",\n" +
                                                "                \"smart-contracts\",\n" +
                                                "                \"staking\",\n" +
                                                "                \"binance-smart-chain\",\n" +
                                                "                \"cardano-ecosystem\"\n" +
                                                "            ],\n" +
                                                "            \"max_supply\": 45000000000,\n" +
                                                "            \"circulating_supply\": 32904527668.666,\n" +
                                                "            \"total_supply\": 33250650235.236,\n" +
                                                "            \"platform\": null,\n" +
                                                "            \"cmc_rank\": 4,\n" +
                                                "            \"last_updated\": \"2021-10-18T18:50:09.000Z\",\n" +
                                                "            \"quote\": {\n" +
                                                "                \"USD\": {\n" +
                                                "                    \"price\": 2.12363958990532,\n" +
                                                "                    \"volume_24h\": 2692266542.977983,\n" +
                                                "                    \"volume_change_24h\": 52.8497,\n" +
                                                "                    \"percent_change_1h\": 0.18337558,\n" +
                                                "                    \"percent_change_24h\": -1.70057402,\n" +
                                                "                    \"percent_change_7d\": -2.39908582,\n" +
                                                "                    \"percent_change_30d\": -10.37036239,\n" +
                                                "                    \"percent_change_60d\": -6.68490158,\n" +
                                                "                    \"percent_change_90d\": 99.08610006,\n" +
                                                "                    \"market_cap\": 69877357644.31412,\n" +
                                                "                    \"market_cap_dominance\": 2.8263,\n" +
                                                "                    \"fully_diluted_market_cap\": 95563781545.74,\n" +
                                                "                    \"last_updated\": \"2021-10-18T18:50:09.000Z\"\n" +
                                                "                }\n" +
                                                "            }\n" +
                                                "        },\n" +
                                                "        {\n" +
                                                "            \"id\": 825,\n" +
                                                "            \"name\": \"Tether\",\n" +
                                                "            \"symbol\": \"USDT\",\n" +
                                                "            \"slug\": \"tether\",\n" +
                                                "            \"num_market_pairs\": 18884,\n" +
                                                "            \"date_added\": \"2015-02-25T00:00:00.000Z\",\n" +
                                                "            \"tags\": [\n" +
                                                "                \"payments\",\n" +
                                                "                \"stablecoin\",\n" +
                                                "                \"stablecoin-asset-backed\",\n" +
                                                "                \"binance-smart-chain\",\n" +
                                                "                \"avalanche-ecosystem\",\n" +
                                                "                \"solana-ecosystem\"\n" +
                                                "            ],\n" +
                                                "            \"max_supply\": null,\n" +
                                                "            \"circulating_supply\": 69036070054.1316,\n" +
                                                "            \"total_supply\": 71385677464.76012,\n" +
                                                "            \"platform\": {\n" +
                                                "                \"id\": 1027,\n" +
                                                "                \"name\": \"Ethereum\",\n" +
                                                "                \"symbol\": \"ETH\",\n" +
                                                "                \"slug\": \"ethereum\",\n" +
                                                "                \"token_address\": \"0xdac17f958d2ee523a2206206994597c13d831ec7\"\n" +
                                                "            },\n" +
                                                "            \"cmc_rank\": 5,\n" +
                                                "            \"last_updated\": \"2021-10-18T18:50:09.000Z\",\n" +
                                                "            \"quote\": {\n" +
                                                "                \"USD\": {\n" +
                                                "                    \"price\": 1.00028442816314,\n" +
                                                "                    \"volume_24h\": 71337358310.22507,\n" +
                                                "                    \"volume_change_24h\": 40.4126,\n" +
                                                "                    \"percent_change_1h\": 0.00746845,\n" +
                                                "                    \"percent_change_24h\": 0.07349882,\n" +
                                                "                    \"percent_change_7d\": -0.03153199,\n" +
                                                "                    \"percent_change_30d\": 0.06283045,\n" +
                                                "                    \"percent_change_60d\": 0.04137914,\n" +
                                                "                    \"percent_change_90d\": 0.00710924,\n" +
                                                "                    \"market_cap\": 69055705856.72751,\n" +
                                                "                    \"market_cap_dominance\": 2.793,\n" +
                                                "                    \"fully_diluted_market_cap\": 71405981561.88,\n" +
                                                "                    \"last_updated\": \"2021-10-18T18:50:09.000Z\"\n" +
                                                "                }\n" +
                                                "            }\n" +
                                                "        }\n" +
                                                "    ]\n" +
                                                "}"
                                )
                        )
        );
    }

}
