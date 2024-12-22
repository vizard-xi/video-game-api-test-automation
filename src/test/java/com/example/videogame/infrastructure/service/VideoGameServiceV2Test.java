package com.example.videogame.infrastructure.service;

import com.example.videogame.domain.model.api.ApiDetails;
import com.example.videogame.domain.model.request.AuthenticationRequest;
import com.example.videogame.domain.model.request.VideoGameRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VideoGameServiceV2Test {

    private VideoGameServiceV2 videoGameServiceV2;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        Properties props = new Properties();
        props.load(AuthenticationServiceTest.class.getClassLoader()
                .getResourceAsStream("application.properties"));

        ApiDetails apiDetails = new ApiDetails(
                props.getProperty("base.url"),
                props.getProperty("api.version.1"),
                props.getProperty("api.version.2"),
                props.getProperty("video.game.path"));

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                props.getProperty("username"),
                props.getProperty("password"));

        AuthenticationService authenticationService = Mockito.spy(new AuthenticationService
                (apiDetails, authenticationRequest));


        videoGameServiceV2 = Mockito.spy(new VideoGameServiceV2(apiDetails, authenticationService
                .getToken().jsonPath().get("token")));
    }

    @AfterEach
    public void tearDown() {
        videoGameServiceV2 = null;
    }

    @Test
    public void testGetAllVideoGames() {

        Response response = videoGameServiceV2.getAllVideoGames();

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json", response.header("Content-Type"));

        assertTrue(response.jsonPath().getList("data").size() > 0);

    }

    @Test
    public void testCreateVideoGame() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "Elden Ring", "RGP", "Mature",
                "2022-02-25 23:59:59", 98);

        Response response = videoGameServiceV2.createVideoGame(videoGameRequest);

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json", response.header("Content-Type"));

        LinkedHashMap responseData = response.jsonPath().get();

        assertNotNull(responseData);

        assertTrue(responseData.containsValue(videoGameRequest.name()));
        assertTrue(responseData.containsValue(videoGameRequest.category()));
        assertTrue(responseData.containsValue(videoGameRequest.rating()));
        assertTrue(responseData.containsValue(videoGameRequest.releaseDate()));
        assertTrue(responseData.containsValue(videoGameRequest.reviewScore()));
        //checking for the ID
        assertTrue(responseData.containsValue(0));
    }

    @Test
    public void testCreateVideoGameWithInvalidRequest() {

        VideoGameRequest videoGameRequest = new VideoGameRequest(
                "", "RGP", "Mature",
                "2022-02-25 23:59:59", 98);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                videoGameServiceV2.createVideoGame(videoGameRequest));


        assertTrue(exception.getMessage()
                .contains("Failed to create a video game: "));

    }


    @Test
    public void testGetAVideoGame() {

        Response response = videoGameServiceV2.getVideoGame(1);

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json", response.header("Content-Type"));

        assertNotNull(response.jsonPath().get());
    }

    @Test
    public void testGetAVideoGameWithNonExistentIdV1() {

        Response response = videoGameServiceV2.getVideoGame(99);

        assertEquals(404, response.getStatusCode());

        assertEquals("application/json", response.header("Content-Type"));

    }

    @Test
    public void testUpdateVideoGame() {

        Integer videoGameId = 1;

        VideoGameRequest updatedVideoGameRequest = new VideoGameRequest(
                "Marvel Rivals", "Hero Shooter", "Mature",
                "2024-12-05 23:59:59", 80);

        Response response = videoGameServiceV2
                .updateVideoGame(videoGameId, updatedVideoGameRequest);

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json", response.header("Content-Type"));

        LinkedHashMap updateVideoGameResponseData = response.jsonPath().get();

        assertNotNull(updateVideoGameResponseData);

        assertTrue(updateVideoGameResponseData.containsValue(updatedVideoGameRequest.name()));
        assertTrue(updateVideoGameResponseData.containsValue(updatedVideoGameRequest.category()));
        assertTrue(updateVideoGameResponseData.containsValue(updatedVideoGameRequest.rating()));
        assertTrue(updateVideoGameResponseData.containsValue(updatedVideoGameRequest.releaseDate()));
        assertTrue(updateVideoGameResponseData.containsValue(updatedVideoGameRequest.reviewScore()));
        //checking for the ID
        assertTrue(updateVideoGameResponseData.containsValue(videoGameId));
    }

    @Test
    public void testUpdateVideoGameWithInvalidId() {

        Integer videoGameId = 99;

        VideoGameRequest updatedVideoGameRequest = new VideoGameRequest(
                "Marvel Rivals", "Hero Shooter", "Mature",
                "2024-12-05 23:59:59", 80);

        Response response = videoGameServiceV2
                .updateVideoGame(videoGameId, updatedVideoGameRequest);

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testUpdateVideoGameWithInvalidData() {

        Integer videoGameId = 1;

        VideoGameRequest updatedVideoGameRequest = new VideoGameRequest(
                "Marvel Rivals", "Hero Shooter", "Mature",
                null, 80);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                videoGameServiceV2.updateVideoGame(videoGameId, updatedVideoGameRequest));


        assertTrue(exception.getMessage()
                .contains("Failed to update a video game: "));
    }

    @Test
    public void testUpdateVideoGameWithNullId() {

        Integer videoGameId = null;

        VideoGameRequest updatedVideoGameRequest = new VideoGameRequest(
                "Marvel Rivals", "Hero Shooter", "Mature",
                null, 80);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                videoGameServiceV2.updateVideoGame(videoGameId, updatedVideoGameRequest));


        assertTrue(exception.getMessage()
                .contains("Failed to update a video game: "));
    }

    @Test
    public void testDeleteAVideoGame() {

        Response response = videoGameServiceV2.deleteVideoGame(1);

        assertEquals(200, response.getStatusCode());

        assertEquals("text/plain;charset=UTF-8", response.header("Content-Type"));

        assertEquals("Video game deleted" , response.getBody().asString());
    }

    @Test
    public void testDeleteAVideoGameWithNonExistentId() {

        Response response = videoGameServiceV2.deleteVideoGame(99);

        assertEquals(404, response.getStatusCode());

        assertEquals("application/json", response.header("Content-Type"));

    }
}