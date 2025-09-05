package com.example.rx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/rxnav")
public class RxNavController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/interaction")
    public ResponseEntity<?> getInteractions(@RequestParam(required = false) String rxcui) {
        try {
            String effectiveRxcui = (rxcui != null) ? rxcui : "34124";
            String url = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=" + effectiveRxcui;
            Object response = restTemplate.getForObject(url, Object.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Unable to fetch data from RxNav API\"}");
        }
    }
}
