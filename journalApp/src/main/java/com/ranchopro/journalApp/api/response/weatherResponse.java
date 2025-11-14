package com.ranchopro.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class weatherResponse {
    private Current current;

    public class AirQuality {
        private String co;
        private String no2;
        private String o3;
        private String so2;
        private String pm2_5;
        private String pm10;

        @JsonProperty("us-epa-index")
        private String us_epa_index;

        @JsonProperty("gb-defra-index")
        private String gb_defra_index;
    }

    public class Astro {
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moon_phase;
        private int moon_illumination;
    }

    @Getter
    @Setter
    public class Current {
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;

        @JsonProperty("weather_code")
        private int weatherCode;
        private List<String> weather_icons;
        private List<String> weather_descriptions;

        private int wind_speed;
        private String wind_dir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        private int uv_index;
        private int visibility;
        private String is_day;
    }


}




