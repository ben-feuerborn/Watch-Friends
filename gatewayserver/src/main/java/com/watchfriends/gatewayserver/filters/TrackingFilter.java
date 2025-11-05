package com.watchfriends.gatewayserver.filters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Order(1)
@Component
public class TrackingFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);
    private final FilterUtils filterUtils;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TrackingFilter(FilterUtils filterUtils) {
        this.filterUtils = filterUtils;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug("tmx-correlation-id found in tracking filter: {}",
                    filterUtils.getCorrelationId(requestHeaders));
        } else {
            String correlationId = generateCorrelationId();
            exchange = filterUtils.setCorrelationId(exchange, correlationId);
            logger.debug("tmx-correlation-id generated in tracking filter: {}", correlationId);
        }

        String username = getUsername(requestHeaders);
        if (!username.isEmpty()) {
            logger.debug("Authenticated user: {}", username);
        }

        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders headers) {
        return filterUtils.getCorrelationId(headers) != null;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    private String getUsername(HttpHeaders headers) {
        String token = filterUtils.getAuthToken(headers);
        if (token == null || !token.startsWith("Bearer ")) {
            return "";
        }

        String jwt = token.substring(7);
        try {
            JsonNode payload = decodeJWT(jwt);
            return payload.path("preferred_username").asText("");
        } catch (Exception e) {
            logger.warn("Failed to parse JWT token: {}", e.getMessage());
            return "";
        }
    }

    private JsonNode decodeJWT(String jwt) throws Exception {
        String[] parts = jwt.split("\\.");
        if (parts.length < 2) {
            return objectMapper.createObjectNode();
        }
        String base64EncodedBody = parts[1];
        String body = new String(Base64.decodeBase64(base64EncodedBody), StandardCharsets.UTF_8);
        return objectMapper.readTree(body);
    }
}
