package com.example.WeatherApp.controllers;

import com.example.WeatherApp.services.WeatherService;
import com.example.WeatherApp.models.Requirements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/request")
    public ResponseEntity<HashMap> getWeatherRequest(@RequestBody Requirements requirements) {
        try {
            HashMap<String, Object> response = weatherService.getWeatherRequest(requirements);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while processing your request.");
            errorResponse.put("details", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
