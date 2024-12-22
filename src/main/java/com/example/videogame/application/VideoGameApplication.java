package com.example.videogame.application;

import com.example.videogame.application.config.VideoGameConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(VideoGameConfig.class)
@SpringBootApplication
public class VideoGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoGameApplication.class, args);
	}

}
