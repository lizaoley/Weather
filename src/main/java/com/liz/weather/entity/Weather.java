package com.liz.weather.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather_history")
public class Weather {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "weather_description", nullable = false, length = 255)
    private String weatherDescription;

    @Column(name = "temperature", nullable = false)
    private double temperature;

    @Column(name = "feels_like", nullable = false)
    private double feelsLike;

    @Column(name = "request_time", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime requestTime;

    public Weather() {
    }

    public Weather(Long id, double latitude, double longitude, String weatherDescription, double temperature, double feelsLike, LocalDateTime requestTime) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.requestTime = requestTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
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

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", requestTime=" + requestTime +
                '}';
    }

}
