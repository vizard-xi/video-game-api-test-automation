package com.example.videogame.infrastructure.service;

import com.example.videogame.domain.model.api.ApiDetails;
import com.example.videogame.domain.model.request.VideoGameRequest;
import com.example.videogame.infrastructure.utils.RequestValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class VideoGameServiceV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoGameServiceV1.class);

    private final ApiDetails apiDetails;

    private final String token;

    public VideoGameServiceV1(ApiDetails apiDetails,
                              String token) {
        this.apiDetails = apiDetails;
        this.token = token;
    }

    public Response getAllVideoGames() {

        try {

            LOGGER.info("Retrieving all video games");

            return RestAssured.given()
                    .baseUri(apiDetails.baseUrl())
                    .basePath(apiDetails.videoGamePath())
                    .contentType(ContentType.JSON)
                    .get();

        } catch (Exception exception) {
            LOGGER.error("Failed to get the list of video games: " + exception.getMessage());
            throw new RuntimeException("Failed to get the list of video games: ", exception);
        }
    }

    public Response createVideoGame(VideoGameRequest videoGameRequest) {

        try {

            validateTokenPresence();

            validateVideoGameRequest(videoGameRequest);

            LOGGER.info("Creating a video game");

            return RestAssured.given()
                    .baseUri(apiDetails.baseUrl())
                    .basePath(apiDetails.videoGamePath())
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .body(videoGameRequest)
                    .post();

        } catch (Exception exception) {
            LOGGER.error("Failed to create a video game: " + exception.getMessage());
            throw new RuntimeException("Failed to create a video game: ", exception);
        }

    }

    public Response getVideoGame(Integer videoGameId) {

        try {

            validateVideoGameId(videoGameId);

            LOGGER.info("Creating a video game");

            return RestAssured.given()
                    .baseUri(apiDetails.baseUrl())
                    .basePath(apiDetails.videoGamePath())
                    .contentType(ContentType.JSON)
                    .pathParam("id", videoGameId)
                    .get("/{id}");

        } catch (Exception exception) {
            LOGGER.error("Failed to find the video game: " + exception.getMessage());
            throw new RuntimeException("Failed to find the video game: ", exception);
        }
    }

    public Response updateVideoGame(Integer videoGameId, VideoGameRequest videoGameRequest) {

        try {

            validateTokenPresence();

            validateVideoGameId(videoGameId);

            validateVideoGameRequest(videoGameRequest);

            LOGGER.info("Updating video game");

            return RestAssured.given()
                    .baseUri(apiDetails.baseUrl())
                    .basePath(apiDetails.videoGamePath())
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .pathParam("id", videoGameId)
                    .body(videoGameRequest)
                    .put("/{id}");

        } catch (Exception exception) {
            LOGGER.error("Failed to update a video game: " + exception.getMessage());
            throw new RuntimeException("Failed to update a video game: ", exception);
        }

    }

    public Response deleteVideoGame(Integer videoGameId) {

        try {

            validateTokenPresence();

            validateVideoGameId(videoGameId);

            LOGGER.info("Updating video game");

            return RestAssured.given()
                    .baseUri(apiDetails.baseUrl())
                    .basePath(apiDetails.videoGamePath())
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + token)
                    .pathParam("id", videoGameId)
                    .delete("/{id}");

        } catch (Exception exception) {
            LOGGER.error("Failed to update a video game: " + exception.getMessage());
            throw new RuntimeException("Failed to update a video game: ", exception);
        }

    }

    private void validateTokenPresence() {
        if (RequestValidator.validateTokenPresence(token)) {
            LOGGER.error("Unable to retrieve token for authorization");
            throw new RuntimeException("Unable to retrieve token for authorization");
        }
    }

    private void validateVideoGameId(Integer videoGameId) {
        if (RequestValidator.validateVideoGameId(videoGameId)) {
            LOGGER.error("Video Game Id is invalid: " + HttpStatus.BAD_REQUEST);
            throw new RuntimeException("Video Game Id is invalid: " + HttpStatus.BAD_REQUEST);
        }
    }

    private void validateVideoGameRequest(VideoGameRequest videoGameRequest) {
        if (RequestValidator.validateVideoGameRequest(videoGameRequest)) {
            LOGGER.error("Video Game request is invalid: " + HttpStatus.BAD_REQUEST);
            throw new RuntimeException("Video Game request is invalid: " + HttpStatus.BAD_REQUEST);
        }
    }
}
