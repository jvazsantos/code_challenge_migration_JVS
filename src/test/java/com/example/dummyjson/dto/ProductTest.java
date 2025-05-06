package com.example.dummyjson.dto;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ProductTest {

    @Test
    public void testGetAndSetter(){
        Long expectId = 1L;
        String expectedTitle = "A dummy title";
        String expectedDescription = "A dummy description";
        Double expectedPrice = Double.valueOf("2.1");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("A dummy title");
        product1.setDescription("A dummy description");
        product1.setPrice(Double.valueOf("2.1"));

        assertThat(expectId).isEqualTo(product1.getId());
        assertThat(expectedTitle).isEqualTo(product1.getTitle());
        assertThat(expectedDescription).isEqualTo(product1.getDescription());
        assertThat(expectedPrice).isEqualTo(product1.getPrice());
    }
}
