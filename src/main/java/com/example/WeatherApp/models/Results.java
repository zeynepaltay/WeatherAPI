package com.example.WeatherApp.models;

import lombok.*;

import java.time.ZonedDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Results {
    private String name;
    private String country;
    private ZonedDateTime localtime;
    private String  condition;
    private Double humudity;
    private Double feelslike_c;
    private Double feelslike_f;
    private Double heatindex_c;
    private Double heatindex_f;
}
