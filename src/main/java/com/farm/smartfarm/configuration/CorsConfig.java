package com.farm.smartfarm.configuration;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        cors.setAllowedMethods(Arrays.asList("GET", "POST"));
        cors.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", cors);
        return new CorsFilter();
    }
}
