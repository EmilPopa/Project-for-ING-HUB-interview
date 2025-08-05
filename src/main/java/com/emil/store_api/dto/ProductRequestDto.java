package com.emil.store_api.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductRequestDto {

    public ProductRequestDto() {
    }

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity must be 0 or more")
    private int quantity;

    @DecimalMin(value = "0.00", message = "Discount must be 0 or more")
    @DecimalMax(value = "100.00", message = "Discount cannot exceed 100")
    private BigDecimal discount;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
