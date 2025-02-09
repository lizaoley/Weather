package com.liz.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.liz.weather.dto.WeatherResponse;
import com.liz.weather.entity.Weather;
import com.liz.weather.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    public WeatherService(RestTemplate restTemplate, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository;
    }

    @Value("${openweathermap.api.key}")
    private String apiKey;

    public WeatherResponse getWeather(double latitude, double longitude) {
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric&lang=ru",
                latitude, longitude, apiKey
        );

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode body = response.getBody();
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setDescription(body.get("weather").get(0).get("description").asText());
        weatherResponse.setTemperature(body.get("main").get("temp").asDouble());
        weatherResponse.setFeelsLike(body.get("main").get("feels_like").asDouble());
        weatherResponse.setImageUrl(getWeatherImage(weatherResponse.getDescription()));
        saveToCashWeather(latitude, longitude, weatherResponse);
        return weatherResponse;
    }

    private void saveToCashWeather(double latitude, double longitude, WeatherResponse weatherResponse) {
        Weather weather = new Weather();
        weather.setLatitude(latitude);
        weather.setLongitude(longitude);
        weather.setWeatherDescription(weatherResponse.getDescription());
        weather.setTemperature(weatherResponse.getTemperature());
        weather.setFeelsLike(weatherResponse.getFeelsLike());
        weather.setRequestTime(LocalDateTime.now());
        weatherRepository.save(weather);
    }

    private String getWeatherImage(String description) {
        return switch (description.toLowerCase()) {
            case "гроза с небольшим дождем", "гроза с дождем", "гроза с сильным дождем", "легкая гроза",
                 "гроза", "сильная гроза", "рваная гроза", "гроза с небольшим моросящим дождем",
                 "гроза с моросящим дождем", "гроза с сильным моросящим дождем" -> "images/гроза.webp";
            case "морось небольшой интенсивности", "морось", "морось сильной интенсивности",
                 "морось с небольшим дождем", "морось с дождем", "морось с сильным дождем",
                 "ливень с моросью", "сильный ливень с моросью" -> "images/морось.webp";
            case "небольшой дождь", "умеренный дождь", "сильный дождь", "очень сильный дождь",
                 "экстремальный дождь", "ледяной дождь", "дождь небольшой интенсивности",
                 "дождь", "рваный дождь" -> "images/дождь.jpg";
            case "небольшой снег", "снег", "сильный снег", "дождь со снегом", "небольшой дождь со снегом",
                 "дождь и снег", "небольшой снегопад", "снегопад", "сильный снегопад" -> "images/снег.jpg";
            case "пасурно", "туман", "мгла", "дымка", "дым" -> "images/пасмурно.jpg";
            case "песчаная/пылевая буря", "песок", "пыль", "вулканический пепел", "шквалы", "торнадо" -> "images/хаус.jpg";
            case "ясно" -> "images/ясно.jpg";
            case "солнечно", "яркое солнце", "безоблачное солнце" -> "images/солнце.jpg";
            case "переменная облачность", "небольшая облачность", "облачно с прояснениями", "разорванные облака",
                 "пасмурно" -> "images/облака.webp";
            default -> "images/дефолт.jpg";
        };
    }

    public List<Weather> getCashWeather() {
        return weatherRepository.findAll();
    }

}
