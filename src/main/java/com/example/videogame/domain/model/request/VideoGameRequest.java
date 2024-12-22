package com.example.videogame.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class VideoGameRequest {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("category")
    private final String category;

    @JsonProperty("rating")
    private final String rating;

    @JsonProperty("releaseDate")
    private final String releaseDate;

    @JsonProperty("reviewScore")
    private final Integer reviewScore;



    public VideoGameRequest(@JsonProperty("name") String name,
                            @JsonProperty("category") String category,
                            @JsonProperty("rating") String rating,
                            @JsonProperty("releaseDate") String releaseDate,
                            @JsonProperty("reviewScore") Integer reviewScore) {
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
    }

    public String name() {
        return name;
    }

    public String category() {
        return category;
    }

    public String rating() {
        return rating;
    }

    public String releaseDate() {
        return releaseDate;
    }

    public Integer reviewScore() {
        return reviewScore;
    }

}
