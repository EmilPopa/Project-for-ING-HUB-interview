package com.emil.store_api.controller;

import com.emil.store_api.dto.ProductRequestDto;
import com.emil.store_api.dto.ProductResponseDto;
import com.emil.store_api.dto.SellProductRequestDto;
import com.emil.store_api.entity.Product;
import com.emil.store_api.mapper.ProductMapper;
import com.emil.store_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService service;

    public ProductController(ProductMapper productMapper, ProductService service) {
        this.productMapper = productMapper;
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = productMapper.fromDto(productRequestDto);
        Product savedProduct = service.save(product);
        log.info("Creating new product : {}", savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.fromEntity(savedProduct));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        log.info("Getting product with id {}", id);
        Product product = service.findProduct(id);
        return ResponseEntity.ok(productMapper.fromEntity(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        log.info("Getting all products");
        List<Product> products = service.listAllProducts();
        return ResponseEntity.ok(products.stream().map(product -> productMapper.fromEntity(product)).collect(Collectors.toList()));
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<ProductResponseDto>> getPaginatedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Getting paginated products: page={}, size={}", page, size);
        List<ProductResponseDto> pagedProducts = service.getPaginatedProducts(page, size)
                .map(productMapper::fromEntity)
                .getContent();
        return ResponseEntity.ok(pagedProducts);
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<ProductResponseDto> updatePrice(@PathVariable Long id, @RequestParam BigDecimal price) {
        log.info("Changing price of product {} to {}", id, price);
        Product updatedProduct = service.changePrice(id, price);
        return ResponseEntity.ok(productMapper.fromEntity(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with id {}", id);
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<ProductResponseDto> sellProduct(@PathVariable Long id, @RequestBody @Valid SellProductRequestDto request) {
        log.info("Selling quantity {} from product {}", request.getQuantity(), id);
        Product updatedProduct = service.sellProduct(id, request.getQuantity());
        return ResponseEntity.ok(productMapper.fromEntity(updatedProduct));
    }

}
