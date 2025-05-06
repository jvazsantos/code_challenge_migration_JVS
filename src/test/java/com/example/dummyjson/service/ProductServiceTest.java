package com.example.dummyjson.service;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dummyjson.dto.Product;
import com.github.tomakehurst.wiremock.client.WireMock;

import java.util.List;

import com.github.tomakehurst.wiremock.WireMockServer;



@SpringBootTest(classes = {
	    ProductService.class,
	    ProductServiceTest.WebClientTestConfig.class
	})
public class ProductServiceTest {

	static WireMockServer wireMockServer;
	
    @Autowired(required=true)
    private ProductService productService;

    // WireMock server base URL
    private static final String MOCK_BASE_URL = "http://localhost:8089";

    @DynamicPropertySource
    static void overrideBaseUrl(DynamicPropertyRegistry registry) {
        registry.add("base.url", () -> MOCK_BASE_URL);
    }

    
    @BeforeAll
    static void startWireMock() {
        wireMockServer = new WireMockServer(8089); 
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);
    }
    
    @AfterAll
    static void stopWireMock() {
        wireMockServer.stop();
    }
    
    @BeforeEach
    void setup() {
        WireMock.configureFor(8089);
        WireMock.resetAllRequests();  // Clean slate before each test
    }

    @Test
    void testGetAllProducts() {
        stubFor(get(urlEqualTo("/products"))
                .willReturn(okJson("""
                        { "products" : [ 
                            { "id": 1, "title": "Product 1"},
                            { "id": 2, "title": "Product 2"}
                            ]
                        }
                        """)));

        List<Product> response = productService.getAllProducts();

        assertThat(response).isNotNull();
        assertThat(response.size()==2);
        assertThat(response.get(0).getTitle()).isEqualTo("Product 1");
    }

    @Test
    void testGetProductById() {
        Long id = 1L;

        stubFor(get(urlEqualTo("/products/1"))
                .willReturn(okJson("""
                        {
                          "id": 1,
                          "title": "Product 1"
                        }
                        """)));

        Product product = productService.getProductById(id);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getTitle()).isEqualTo("Product 1");
    }

    // Define a custom WebClient bean for the test using the mock base URL
    @Configuration
    static class WebClientTestConfig {
        @Bean
        public WebClient webClient() {
            return WebClient.builder()
                    .baseUrl(MOCK_BASE_URL)
                    .build();
        }
    }
}