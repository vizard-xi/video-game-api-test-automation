package com.example.videogame.domain.model.api;

public class ApiDetails {

    private final String baseUrl;

    private final String apiVersion1;

    private final String apiVersion2;

    private final String videoGamePath;

    public ApiDetails(String baseUrl, String apiVersion1, String apiVersion2, String videoGamePath) {
        this.baseUrl = baseUrl;
        this.apiVersion1 = apiVersion1;
        this.apiVersion2 = apiVersion2;
        this.videoGamePath = videoGamePath;
    }

    public String baseUrl() {
        return baseUrl;
    }

    public String apiVersion1() {
        return apiVersion1;
    }

    public String apiVersion2() {
        return apiVersion2;
    }

    public String videoGamePath() {
        return videoGamePath;
    }
}
