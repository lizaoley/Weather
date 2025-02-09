package com.liz.weather.controller;

import com.liz.weather.dto.WeatherResponse;
import com.liz.weather.entity.Weather;
import com.liz.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/weather")
public class WeatherController {

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherResponse getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.getWeather(latitude, longitude);
    }

    @GetMapping("/history")
    public List<Weather> getCashWeather() {
        return weatherService.getCashWeather();
    }





}
