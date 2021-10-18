package com.interview.crealogix.initializer;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start();

        applicationContext.addApplicationListener(event -> {
            if (event instanceof ContextClosedEvent) {
                wireMockServer.stop();
            }
        });
        applicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

        final String wireMockServerUrl = String.format("http://localhost:%s", wireMockServer.port());
        TestPropertyValues.of(
                String.format("coinmarket.root.url=%s", wireMockServerUrl)
        ).applyTo(applicationContext);
    }
}
