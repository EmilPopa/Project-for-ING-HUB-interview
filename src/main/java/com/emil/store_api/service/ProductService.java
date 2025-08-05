package com.emil.store_api.service;

import com.emil.store_api.entity.Product;
import com.emil.store_api.exception.InsufficientStockException;
import com.emil.store_api.exception.ProductNotFoundException;
import com.emil.store_api.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product save(Product product) {
        log.info("Saving product: {}", product);
        return repository.save(product);
    }

    public Product findProduct(Long id) {
        log.debug("Looking for product with ID {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product with ID {} not found", id);
                    return new ProductNotFoundException(id);
                });
    }

    public List<Product> listAllProducts() {
        log.debug("Retrieving all products");
        return repository.findAll();
    }

    public Page<Product> getPaginatedProducts(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Product changePrice(Long id, BigDecimal newPrice) {
        log.info("Changing price of product ID {} to {}", id, newPrice);
        Product product = findProduct(id);
        product.setPrice(newPrice);
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        log.info("Deleting product with ID {}", id);
        if (!repository.existsById(id)) {
            log.warn("Product with ID {} not found. Cannot delete.", id);
            throw new ProductNotFoundException(id);
        }
        repository.deleteById(id);
        log.info("Product with ID {} deleted successfully", id);
    }

    public Product sellProduct(Long id, int quantity) {
        log.info("Attempting to sell {} units of product ID {}", quantity, id);
        Product product = findProduct(id);
        if (product.getQuantity() < quantity) {
            log.warn("Insufficient stock for product ID {}: requested {}, available {}",
                    id, quantity, product.getQuantity());
            throw new InsufficientStockException("Insufficient stock");
        }
        product.setQuantity(product.getQuantity() - quantity);
        log.info("Sold {} units of product ID {}. Remaining stock: {}", quantity, id, product.getQuantity());
        return repository.save(product);
    }
}
