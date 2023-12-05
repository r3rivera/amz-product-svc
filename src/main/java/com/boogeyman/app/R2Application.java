package com.boogeyman.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class R2Application {

	public static void main(String[] args) {
		log.info("### Starting.... ");
		SpringApplication.run(R2Application.class, args);
	}


}
