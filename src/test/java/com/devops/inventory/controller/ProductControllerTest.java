package com.devops.inventory.controller;

import com.devops.inventory.model.Product;
import com.devops.inventory.service.ProductService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    @Test
    void shouldCreateProductThroughController() {
        ProductController controller = new ProductController(new ProductService());

        Product product = new Product(null, "Monitor", "Electronics", 8,
                new BigDecimal("15000.00"));

        Product result = controller.createProduct(product);

        assertNotNull(result.getId());
        assertEquals("Monitor", result.getName());
    }
}
