package com.example.videogame.infrastructure.service;

import com.example.videogame.domain.model.api.ApiDetails;
import com.example.videogame.domain.model.request.AuthenticationRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    private ApiDetails apiDetails;

    @BeforeEach
    public void setUp() throws IOException {

        MockitoAnnotations.openMocks(this);

        Properties props = new Properties();
        props.load(AuthenticationServiceTest.class.getClassLoader()
                .getResourceAsStream("application.properties"));

        apiDetails = new ApiDetails(
                props.getProperty("base.url"),
                props.getProperty("api.version.1"),
                props.getProperty("api.version.2"),
                props.getProperty("video.game.path")
        );

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                props.getProperty("username"),
                props.getProperty("password"));

        authenticationService = Mockito.spy(new AuthenticationService
                (apiDetails, authenticationRequest));
    }

    @AfterEach
    public void tearDown() {
        authenticationService = null;
    }

    @Test
    public void testTokenRetrievalWithValidCredentials() {

        Response response = authenticationService.getToken();

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json", response.header("Content-Type"));

        String token = response.jsonPath().getString("token");

        assertNotNull(token);

    }

    @Test
    public void testTokenRetrievalWithInvalidCredentials() {

        authenticationService = new AuthenticationService(apiDetails,
                new AuthenticationRequest("test", "test"));

        Response response = authenticationService.getToken();

        assertNotEquals(200, response.getStatusCode());

        assertEquals("application/json", response.header("Content-Type"));

        String token = response.jsonPath().getString("token");

        assertNull(token);

    }

    @Test
    public void testTokenRetrievalWithEmptyCredentials() {

        authenticationService = new AuthenticationService(apiDetails,
                new AuthenticationRequest("", ""));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authenticationService.getToken());

        assertTrue(exception.getMessage().contains("Username and password are required. " + HttpStatus.BAD_REQUEST));
    }

    @Test
    public void testTokenRetrievalWithNullCredentials() {

        authenticationService = new AuthenticationService(apiDetails,
                new AuthenticationRequest(null, null));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authenticationService.getToken());

        assertTrue(exception.getMessage().contains("Username and password are required. " + HttpStatus.BAD_REQUEST));

    }

}