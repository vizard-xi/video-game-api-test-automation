package com.example.videogame.domain.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class AuthenticationRequest {

    @JsonProperty("username")
    private final String username;

    @JsonProperty("password")
    private final String password;

    public AuthenticationRequest(@JsonProperty("username") String username,
                                 @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
