package com.adventureforge.gameservice.services.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Component
public class AdventureRestTemplateClient {

    RestTemplate restTemplate;
}
