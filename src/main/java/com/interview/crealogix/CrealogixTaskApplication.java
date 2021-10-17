package com.interview.crealogix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrealogixTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrealogixTaskApplication.class, args);
    }

}
