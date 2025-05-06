
package com.example.dummyjson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebClientConfig {

	@Value("${app.apiURL}")
	private String BASE_URL;

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		System.out.println("************* " + BASE_URL);
		return builder.baseUrl(BASE_URL).build();
	}
}
