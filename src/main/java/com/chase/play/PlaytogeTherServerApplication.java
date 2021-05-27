package com.chase.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PlaytogeTherServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaytogeTherServerApplication.class, args);
    }

}
