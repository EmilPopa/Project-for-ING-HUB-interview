package com.emil.store_api.mapper;

import com.emil.store_api.dto.ProductRequestDto;
import com.emil.store_api.dto.ProductResponseDto;
import com.emil.store_api.entity.Category;
import com.emil.store_api.entity.Product;
import com.emil.store_api.exception.CategoryNotFoundException;
import com.emil.store_api.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Product fromDto(ProductRequestDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryId()));

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setDiscount(dto.getDiscount());
        product.setCategory(category);
        return product;
    }

    public ProductResponseDto fromEntity(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setDiscount(product.getDiscount());
        dto.setCategoryName(
                product.getCategory() != null ? product.getCategory().getName() : null
        );
        return dto;
    }
}
