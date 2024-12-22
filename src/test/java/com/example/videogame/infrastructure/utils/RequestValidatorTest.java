package com.example.videogame.infrastructure.utils;

import com.example.videogame.domain.model.request.AuthenticationRequest;
import com.example.videogame.domain.model.request.VideoGameRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestValidatorTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testValidAuthenticationRequest() {

        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("test", "test");

        boolean actual = RequestValidator.validateAuthenticationRequest(authenticationRequest);

        assertFalse(actual);

    }

    @Test
    public void testMissingUsernameAuthenticationRequest() {

        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("", "test");

        boolean actual = RequestValidator.validateAuthenticationRequest(authenticationRequest);

        assertTrue(actual);

    }

    @Test
    public void testMissingPasswordAuthenticationRequest() {

        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("test", "");

        boolean actual = RequestValidator.validateAuthenticationRequest(authenticationRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullUsernameAuthenticationRequest() {

        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest(null, "test");

        boolean actual = RequestValidator.validateAuthenticationRequest(authenticationRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullPasswordAuthenticationRequest() {

        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("test", null);

        boolean actual = RequestValidator.validateAuthenticationRequest(authenticationRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullUsernameAndPasswordAuthenticationRequest() {

        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest(null, null);

        boolean actual = RequestValidator.validateAuthenticationRequest(authenticationRequest);

        assertTrue(actual);

    }

    @Test
    public void testEmptyUsernameAndPasswordAuthenticationRequest() {

        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("", "");

        boolean actual = RequestValidator.validateAuthenticationRequest(authenticationRequest);

        assertTrue(actual);

    }



    @Test
    public void testValidVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", "RGP", "Mature",
                "2022-02-25 23:59:59", 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertFalse(actual);

    }

    @Test
    public void testEmptyNameVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "", "RGP", "Mature",
                "2022-02-25 23:59:59", 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullNameVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                null, "RGP", "Mature",
                "2022-02-25 23:59:59", 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testEmptyCategoryVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", "", "Mature",
                "2022-02-25 23:59:59", 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullCategoryVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", null, "Mature",
                "2022-02-25 23:59:59", 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testEmptyRatingVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", "RPG", "",
                "2022-02-25 23:59:59", 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullRatingVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", "RPG", null,
                "2022-02-25 23:59:59", 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testEmptyReleaseDateVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", "RPG", "Mature",
                "", 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullReleaseDateVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", "RPG", "Mature",
                null, 98);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullReviewScoreVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", "RPG", "Mature",
                "2022-02-25 23:59:59", null);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testNullVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                null, null, null,
                null, null);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testEmptyVideoGameRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "", "", "",
                "", null);

        boolean actual = RequestValidator.validateVideoGameRequest(videoGameRequest);

        assertTrue(actual);

    }

    @Test
    public void testValidTokenPresence() {

        String token = "someToken";

        boolean actual = RequestValidator.validateTokenPresence(token);

        assertFalse(actual);
    }

    @Test
    public void testEmptyToken() {

        String token = "";

        boolean actual = RequestValidator.validateTokenPresence(token);

        assertTrue(actual);
    }

    @Test
    public void testNullToken() {

        String token = null;

        boolean actual = RequestValidator.validateTokenPresence(token);

        assertTrue(actual);
    }

    @Test
    public void testValidVideoGameId() {

        Integer videoGameId = 1;

        boolean actual = RequestValidator.validateVideoGameId(videoGameId);

        assertFalse(actual);

    }

    @Test
    public void testNullVideoGameId() {

        Integer videoGameId = null;

        boolean actual = RequestValidator.validateVideoGameId(videoGameId);

        assertTrue(actual);

    }

    @Test
    public void testNegativeVideoGameId() {

        Integer videoGameId = -1;

        boolean actual = RequestValidator.validateVideoGameId(videoGameId);

        assertTrue(actual);

    }
}