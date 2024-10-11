package com.movierecommendationsystem.movierecommendationsystem_backend.entity;

public enum MediaType {
    MOVIE("movie"),
    TV_SHOW("tv show");

    private final String value;

    MediaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MediaType fromValue(String value) {
        for (MediaType type : MediaType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown media type: " + value);
    }
    
}
