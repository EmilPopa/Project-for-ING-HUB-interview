package com.emil.store_api.dto;

import jakarta.validation.constraints.Min;

public class SellProductRequestDto {

    @Min(1)
    private int quantity;

    public SellProductRequestDto(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

