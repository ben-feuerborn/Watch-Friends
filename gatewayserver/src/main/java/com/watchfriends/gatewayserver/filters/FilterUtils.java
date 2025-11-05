package com.watchfriends.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtils {

    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";

    public String getCorrelationId(HttpHeaders headers) {
        return headers.getFirst(CORRELATION_ID);
    }

    public String getAuthToken(HttpHeaders headers) {
        return headers.getFirst(AUTH_TOKEN);
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return exchange.mutate()
                .request(builder -> builder.header(CORRELATION_ID, correlationId))
                .build();
    }
}