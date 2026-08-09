package com.devops.inventory.service;

import com.devops.inventory.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void shouldReturnInitialProducts() {
        ProductService service = new ProductService();

        assertEquals(2, service.findAll().size());
    }

    @Test
    void shouldCreateProduct() {
        ProductService service = new ProductService();

        Product product = new Product(null, "Keyboard", "Electronics", 20,
                new BigDecimal("1200.00"));

        Product created = service.create(product);

        assertNotNull(created.getId());
        assertEquals("Keyboard", created.getName());
        assertEquals(3, service.findAll().size());
    }

    @Test
    void shouldUpdateProduct() {
        ProductService service = new ProductService();

        Product updated = new Product(null, "Gaming Laptop", "Electronics", 5,
                new BigDecimal("95000.00"));

        Product result = service.update(1L, updated);

        assertEquals("Gaming Laptop", result.getName());
        assertEquals(5, result.getQuantity());
    }

    @Test
    void shouldDeleteProduct() {
        ProductService service = new ProductService();

        service.delete(1L);

        assertEquals(1, service.findAll().size());
        assertThrows(ProductNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void shouldThrowWhenProductDoesNotExist() {
        ProductService service = new ProductService();

        assertThrows(ProductNotFoundException.class, () -> service.findById(999L));
    }
}
