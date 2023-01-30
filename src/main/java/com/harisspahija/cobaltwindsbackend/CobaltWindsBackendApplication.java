package com.harisspahija.cobaltwindsbackend;

import com.harisspahija.cobaltwindsbackend.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(SecurityConfig.class)
@SpringBootApplication
public class CobaltWindsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobaltWindsBackendApplication.class, args);
    }
}

