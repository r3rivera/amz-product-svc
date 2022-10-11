package com.boogeyman.app;

// import graphql.kickstart.tools.SchemaParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
public class ChatBotApplication {

	public static void main(String[] args) {
		log.info("### Starting.... ");
		SpringApplication.run(ChatBotApplication.class, args);
	}

}
