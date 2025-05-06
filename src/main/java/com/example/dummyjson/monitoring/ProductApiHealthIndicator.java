
package com.example.dummyjson.monitoring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductApiHealthIndicator implements HealthIndicator {

    private final WebClient webClient;
    private final String healthCheckUrl;

    public ProductApiHealthIndicator(
            WebClient.Builder webClientBuilder,
            @Value("${app.apiURL}") String healthCheckUrl
    ) {
        this.webClient = webClientBuilder.build();
        this.healthCheckUrl = healthCheckUrl;
    }

    @Override
    public Health health() {
        try {
            String result = webClient.get()
                    .uri(healthCheckUrl+"/products/1")
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorResume(ex -> Mono.empty())
                    .blockOptional()
                    .orElse(null);

            if (result != null) {
                return Health.up().withDetail("Product API", "Available").build();
            } else {
                return Health.down().withDetail("Product API", "No response").build();
            }
        } catch (Exception ex) {
            return Health.down(ex).withDetail("Product API", "Error").build();
        }
    }
}
