package com.example.WeatherApp.models;

import lombok.*;
import com.example.WeatherApp.models.enums.TimeEnums;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class Requirements {
    private String city;
    private String country;
    private TimeEnums timeEnum;
}
