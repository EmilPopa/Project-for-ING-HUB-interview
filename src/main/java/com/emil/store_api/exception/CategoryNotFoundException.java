package com.emil.store_api.exception;

import com.emil.store_api.entity.Category;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Category with id " + id + " not found");
    }
}