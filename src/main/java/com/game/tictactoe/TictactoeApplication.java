package com.game.tictactoe;

import com.game.tictactoe.controller.BoardController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TictactoeApplication {

    @Bean
    ResourceConfig resourceConfig() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(BoardController.class);
        resourceConfig.property(ServletProperties.FILTER_FORWARD_ON_404, true);
        return resourceConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(TictactoeApplication.class, args);
    }

}
