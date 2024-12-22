package com.example.videogame.infrastructure.utils;

import com.example.videogame.domain.model.request.AuthenticationRequest;
import com.example.videogame.domain.model.request.VideoGameRequest;

public class RequestValidator {

    public static boolean validateAuthenticationRequest(AuthenticationRequest authenticationRequest) {
        return authenticationRequest.username() == null || authenticationRequest.username().isEmpty()
                || authenticationRequest.password() == null || authenticationRequest.password().isEmpty();
    }

    public static boolean validateVideoGameRequest(VideoGameRequest videoGameRequest) {
        return videoGameRequest.name() == null || videoGameRequest.name().isEmpty() ||
                videoGameRequest.category() == null || videoGameRequest.category().isEmpty() ||
                videoGameRequest.rating() == null || videoGameRequest.rating().isEmpty() ||
                videoGameRequest.releaseDate() == null || videoGameRequest.releaseDate().isEmpty() ||
                videoGameRequest.reviewScore() == null;
    }

    public static boolean validateTokenPresence(String token) {
        return token == null || token.isEmpty();
    }

    public static boolean validateVideoGameId(Integer videoGameId) {
        return videoGameId == null || videoGameId < 0;
    }
}
