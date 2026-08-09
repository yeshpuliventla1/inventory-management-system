package com.devops.inventory.service;

import com.devops.inventory.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ProductService() {
        // Sample inventory data for the learning project.
        products.add(new Product(idGenerator.getAndIncrement(), "Laptop", "Electronics", 10,
                new java.math.BigDecimal("75000.00")));
        products.add(new Product(idGenerator.getAndIncrement(), "Office Chair", "Furniture", 25,
                new java.math.BigDecimal("8500.00")));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product create(Product product) {
        product.setId(idGenerator.getAndIncrement());
        products.add(product);
        return product;
    }

    public Product update(Long id, Product updatedProduct) {
        Product existing = findById(id);
        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setQuantity(updatedProduct.getQuantity());
        existing.setPrice(updatedProduct.getPrice());
        return existing;
    }

    public void delete(Long id) {
        Product existing = findById(id);
        products.remove(existing);
    }
}
