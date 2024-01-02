package com.jar.KiranaRegister.Services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service

public class ApiService {
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    ObjectMapper objectMapper = new ObjectMapper();
    private final String apiUrl = "https://api.fxratesapi.com/latest/";

    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public double fetchDataFromApi() throws JsonProcessingException {
        logger.info("Entered fetchDataFromApi of ApiService");


        // Make GET request
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if(response.getBody().isEmpty())
        {
            logger.info("Response body is empty");
        }
        double inrRate=0;

        // Process the response
        if (response.getStatusCode().is2xxSuccessful()) {

            try {
                JsonNode jsonResponse = objectMapper.readTree(response.getBody());

                // Retrieve the exchange rate for INR
                inrRate = jsonResponse
                        .path("rates")
                        .path("INR")
                        .asDouble();

                System.out.println("Exchange Rate for INR: " + inrRate);
            } catch (IOException e) {
                logger.info("Exception while currency conversion");
            }

        }
        else {
            // Handle non-successful response
            System.err.println("Error: " + response.getStatusCode());
        }

        return inrRate;

    }
}