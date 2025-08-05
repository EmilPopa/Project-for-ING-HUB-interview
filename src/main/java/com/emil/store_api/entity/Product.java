package com.emil.store_api.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private int quantity;

    private BigDecimal discount = BigDecimal.ZERO; // procent (ex: 10 pentru 10%)

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}

    public Product(String name, BigDecimal price, int quantity, BigDecimal discount, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // === LOGICĂ DE AFIȘARE PREȚ CU DISCOUNT ===
    public BigDecimal getPriceWithDiscount() {
        if (discount == null || discount.compareTo(BigDecimal.ZERO) <= 0) {
            return price;
        }
        return price.subtract(price.multiply(discount).divide(BigDecimal.valueOf(100)));
    }
}
