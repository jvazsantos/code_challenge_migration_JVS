package com.example.dummyjson.config;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = WebClientConfig.class)
public class WebClientConfigTest {

	@Autowired
	private WebClient.Builder builder;
	
	@Value("${app.apiURL}")
	private String baseUrl;

	@Test
	void builderShouldCreateWebClientWithBaseUrl() {
	    WebClient client = builder.baseUrl(baseUrl).build();
	    assertThat(client).isNotNull();
	}}
