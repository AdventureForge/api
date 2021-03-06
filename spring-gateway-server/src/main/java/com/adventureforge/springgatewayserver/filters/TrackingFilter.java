package com.adventureforge.springgatewayserver.filters;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Order(1)
@Component
public class TrackingFilter implements GlobalFilter {

    FilterUtils filterUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)) {
            log.debug("tmx-correlation-id found in tracking filter: {}. ",
                    filterUtils.getCorrelationId(requestHeaders));
        } else {
            String correlationID = generateCorrelationId();
            exchange = filterUtils.setCorrelationId(exchange, correlationID);
            log.debug("tmx-correlation-id generated in tracking filter: {}.", correlationID);
        }
        //log.debug("The authentication name from the token is : " + getUsername(requestHeaders));

        return chain.filter(exchange);
    }


    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return filterUtils.getCorrelationId(requestHeaders).isPresent();
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    private String getUsername(HttpHeaders requestHeaders) {
        String username = "";
        Optional<String> authToken = filterUtils.getAuthToken(requestHeaders);
        if (authToken.isPresent()) {
            String token = authToken.get().replace("Bearer ", "");
            JSONObject jsonObj = decodeJWT(token);
            try {
                username = jsonObj.getString("preferred_username");
            } catch (Exception e) {
                log.debug(e.getMessage());
            }
        }
        return username;
    }

    private JSONObject decodeJWT(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        return new JSONObject(payload);
    }
}