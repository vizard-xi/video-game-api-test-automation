package com.example.videogame.application.config;

import com.example.videogame.domain.model.api.ApiDetails;
import com.example.videogame.domain.model.request.AuthenticationRequest;
import com.example.videogame.infrastructure.service.AuthenticationService;
import com.example.videogame.infrastructure.service.VideoGameServiceV1;
import com.example.videogame.infrastructure.service.VideoGameServiceV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoGameConfig {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${api.version.1}")
    private String apiVersion1;

    @Value("${api.version.2}")
    private String apiVersion2;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${video.game.path}")
    private String videoGamePath;

    @Bean
    public AuthenticationService authenticationService(ApiDetails apiDetails) {
        return new AuthenticationService(apiDetails,
                new AuthenticationRequest(username, password));
    }

    @Bean
    public VideoGameServiceV1 videoGameServiceV1(ApiDetails apiDetails) {
        return new VideoGameServiceV1(apiDetails, authenticationService(apiDetails).getToken()
                .jsonPath().get("token"));
    }

    @Bean
    public VideoGameServiceV2 videoGameServiceV2(ApiDetails apiDetails) {
        return new VideoGameServiceV2(apiDetails, authenticationService(apiDetails).getToken()
                .jsonPath().get("token"));
    }

    @Bean
    public ApiDetails apiDetails() {
        return new ApiDetails(baseUrl, apiVersion1, apiVersion2, videoGamePath);
    }

}
