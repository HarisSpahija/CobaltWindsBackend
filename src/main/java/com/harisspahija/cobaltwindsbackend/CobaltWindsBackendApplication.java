package com.harisspahija.cobaltwindsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CobaltWindsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobaltWindsBackendApplication.class, args);
    }
}

