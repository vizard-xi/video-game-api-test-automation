package com.example.videogame.infrastructure.service;

import com.example.videogame.domain.model.api.ApiDetails;
import com.example.videogame.domain.model.request.AuthenticationRequest;
import com.example.videogame.infrastructure.utils.RequestValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private final ApiDetails apiDetails;

    private final AuthenticationRequest authenticationRequest;

    public AuthenticationService(ApiDetails apiDetails, AuthenticationRequest authenticationRequest) {
        this.apiDetails = apiDetails;
        this.authenticationRequest = authenticationRequest;
    }

    public Response getToken() {

        LOGGER.info("Validating user credentials");

        if (RequestValidator.validateAuthenticationRequest(authenticationRequest)) {
            LOGGER.error("Username and password are required. " + HttpStatus.BAD_REQUEST);
            throw new RuntimeException("Username and password are required. " + HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("User credentials has been successfully validated");

        try {

            LOGGER.info("Retrieving token for user");

            return RestAssured.given()
                    .baseUri(apiDetails.baseUrl())
                    .basePath("authenticate")
                    .contentType(ContentType.JSON)
                    .body(authenticationRequest)
                    .post();

        } catch (Exception exception) {
            LOGGER.error("Error during authentication: " + exception.getMessage());
            throw new RuntimeException("Failed to retrieve token", exception);
        }
    }
}
