package com.liz.weather.dto;

public class WeatherResponse {

    private String description;
    private double temperature;
    private double feelsLike;
    private String imageUrl;

    public WeatherResponse() {
    }

    public WeatherResponse(String description, double temperature, double feelsLike, String imageUrl) {
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
