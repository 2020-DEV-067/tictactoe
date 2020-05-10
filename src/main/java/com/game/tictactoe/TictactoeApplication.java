package com.game.tictactoe;

import com.game.tictactoe.controller.BoardController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TictactoeApplication {

	@Bean
	ResourceConfig resourceConfig() {
		return new ResourceConfig().register(BoardController.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}

}
