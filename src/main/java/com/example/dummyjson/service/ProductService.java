package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
//import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    private final String PRODUCTS_PATH = "/products";
    private final String PRODUCTS_ID_PATH = "/products/{id}";
    
    

//    @Autowired
//    private RestTemplate restTemplate;
    
    @Autowired
    private WebClient webClient;


    public List<Product> getAllProducts() {
    	ProductResponse productResponse = webClient.get().uri(PRODUCTS_PATH).retrieve().bodyToMono(ProductResponse.class).block();   
       
    	return productResponse.getProducts();
    }
       

    public Product getProductById(Long id) {
        return webClient.get().uri(PRODUCTS_ID_PATH, id).retrieve().bodyToMono(Product.class).block();
    }
}
