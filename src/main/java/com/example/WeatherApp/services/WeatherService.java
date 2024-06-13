package com.example.WeatherApp.services;

import com.example.WeatherApp.models.Requirements;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;
    public HashMap getWeatherRequest(Requirements requirements) {
        switch(requirements.getTimeEnum()){
            case DAY -> {
                return getCurrentWeather(requirements);
            }
            case WEEK -> {
                return getForecast(requirements,7);
            }
            case MONTH -> {
                return getForecast(requirements,12);
            }
            default -> {
                return null;
            }
        }
    }

    private HashMap  getCurrentWeather(Requirements requirements) {


        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://api.weatherapi.com/v1/current.json";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("key", apiKey)
                .queryParam("q", requirements.getCity())
                .queryParam("aqi","no");
        HashMap result= null;
        try {
            result = restTemplate.getForObject(builder.toUriString(), HashMap.class);
        } catch (RestClientException e) {
            System.err.println("Error occurred while making API call: " + e.getMessage());
        }

        return result;
    }

    private HashMap getForecast(Requirements requirements,Integer numberOfDays) {

        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://api.weatherapi.com/v1/forecast.json";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("key", apiKey)
                .queryParam("q", requirements.getCity())
                .queryParam("aqi","no")
                .queryParam("days",numberOfDays)
                .queryParam("alerts","no");
        HashMap result=null;
        try {
            result = restTemplate.getForObject(builder.toUriString(), HashMap.class);
        } catch (RestClientException e) {
            System.err.println("Error occurred while making API call: " + e.getMessage());
        }
        return result;
    }


}
