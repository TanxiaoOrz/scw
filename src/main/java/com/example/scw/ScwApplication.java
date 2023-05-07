package com.example.scw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScwApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwApplication.class, args);
    }

}
