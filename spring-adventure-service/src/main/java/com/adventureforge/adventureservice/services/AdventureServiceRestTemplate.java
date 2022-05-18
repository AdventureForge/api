package com.adventureforge.adventureservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Component
public class AdventureServiceRestTemplate {

    RestTemplate restTemplate;
}
