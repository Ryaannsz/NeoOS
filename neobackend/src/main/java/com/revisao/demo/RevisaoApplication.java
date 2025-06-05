package com.revisao.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@EnableScheduling

public class RevisaoApplication {

    public static void main(String[] args) {
	SpringApplication.run(RevisaoApplication.class, args);
    }

}
