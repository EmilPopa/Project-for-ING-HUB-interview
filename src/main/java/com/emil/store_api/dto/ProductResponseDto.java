package com.emil.store_api.dto;

import java.math.BigDecimal;

public class ProductResponseDto {
        private Long id;
        private String name;
        private BigDecimal price;
        private BigDecimal discount;
        private BigDecimal priceWithDiscount;
        private int quantity;
        private String categoryName;

        public ProductResponseDto() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

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

        public BigDecimal getDiscount() {
                return discount;
        }

        public void setDiscount(BigDecimal discount) {
                this.discount = discount;
        }

        public BigDecimal getPriceWithDiscount() {
                return priceWithDiscount;
        }

        public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
                this.priceWithDiscount = priceWithDiscount;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

        public String getCategoryName() {
                return categoryName;
        }

        public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
        }
}
