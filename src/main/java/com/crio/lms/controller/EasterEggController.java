package com.crio.lms.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/easter-egg")
public class EasterEggController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<Map<String, String>> getNumberFact(@PathVariable int number) {
        String url = "http://numbersapi.com/" + number;
        String fact = restTemplate.getForObject(url, String.class);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Great! You have found the hidden number fact");
        response.put("response", fact + ".");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
