package com.ranchopro.journalApp.service;

import com.ranchopro.journalApp.api.response.weatherResponse;
import com.ranchopro.journalApp.cache.AppCache;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WeatherService {
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisSevice redisSevice;

    public weatherResponse getWeather(String city) {
        weatherResponse catchedResponse = redisSevice.get("Weather_of_" + city, weatherResponse.class);
        if (catchedResponse != null) {
            log.warn("Data isfetched from Redis");
            return catchedResponse; // if the data is catched then return it else collect the response from backend
        } else {
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
            ResponseEntity<weatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, weatherResponse.class);
            weatherResponse body = response.getBody();
            if (body != null) {
                redisSevice.set("Weather_of_" + city, body, 1200l);

            }
            return body;
        }
    }

}
