package com.liz.weather.front;

import com.liz.weather.dto.WeatherResponse;
import com.liz.weather.entity.Weather;
import com.liz.weather.service.WeatherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
@CssImport("./styles/shared-styles.css")
public class WeatherFront extends VerticalLayout {

    private final WeatherService weatherService;

    public WeatherFront(WeatherService weatherService) {
        this.weatherService = weatherService;

        TextField latitudeField = new TextField("Широта");
        TextField longitudeField = new TextField("Долгота");
        latitudeField.setWidth("150px");
        longitudeField.setWidth("150px");
        Button getWeatherButton = new Button("Показать погоду!");

        HorizontalLayout inputFieldsLayout = new HorizontalLayout(latitudeField, longitudeField);
        inputFieldsLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        inputFieldsLayout.setSpacing(true);

        H2 weatherDescription = new H2();
        weatherDescription.getStyle().set("text-align", "center");

        Paragraph temperature = new Paragraph();
        temperature.getStyle().set("text-align", "center");

        Image weatherImage = new Image("images/дефолт.jpg", "Изображение погоды не загружено");
        weatherImage.setWidth("600px");

        getWeatherButton.addClickListener(event -> {
            if (latitudeField.isEmpty() || longitudeField.isEmpty()) {
                weatherDescription.setText("Введите координаты!");
                return;
            }

            double latitude;
            double longitude;
            try {
                latitude = Double.parseDouble(latitudeField.getValue());
                longitude = Double.parseDouble(longitudeField.getValue());
            } catch (NumberFormatException e) {
                weatherDescription.setText("Ошибка: некорректные координаты!");
                return;
            }

            WeatherResponse response = weatherService.getWeather(latitude, longitude);
            weatherDescription.setText(response.getDescription());
            temperature.setText(response.getTemperature() + " градусов, ощущается как " + response.getFeelsLike());
            weatherImage.setSrc(response.getImageUrl());
        });

        Grid<Weather> cashGrid = new Grid<>(Weather.class);
        cashGrid.setColumns("latitude", "longitude", "weatherDescription", "temperature", "feelsLike", "requestTime");
        cashGrid.setItems(weatherService.getCashWeather());

        VerticalLayout centeredContent = new VerticalLayout(weatherDescription, temperature, weatherImage);
        centeredContent.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        centeredContent.setSpacing(true);

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(inputFieldsLayout, getWeatherButton, centeredContent, cashGrid);
        setSpacing(true);
    }
}
