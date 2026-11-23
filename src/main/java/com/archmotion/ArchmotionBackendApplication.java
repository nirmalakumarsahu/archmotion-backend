package com.archmotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ArchmotionBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchmotionBackendApplication.class, args);
    }

}
