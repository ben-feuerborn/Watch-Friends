package com.watchfriends.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);
    private final FilterUtils filterUtils;

    public ResponseFilter(FilterUtils filterUtils) {
        this.filterUtils = filterUtils;
    }

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> addCorrelationIdToResponse(exchange)));
    }

    private void addCorrelationIdToResponse(ServerWebExchange exchange) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        String correlationId = filterUtils.getCorrelationId(requestHeaders);

        if (correlationId != null && !correlationId.isEmpty()) {
            exchange.getResponse().getHeaders().add(FilterUtils.CORRELATION_ID, correlationId);
            logger.debug("Added correlation ID [{}] to outbound response headers.", correlationId);
        } else {
            logger.debug("No correlation ID found in request headers; skipping response header addition.");
        }

        logger.debug("Completed outgoing request for [{}]", exchange.getRequest().getURI());
    }
}
