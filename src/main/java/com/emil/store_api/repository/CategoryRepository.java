package com.emil.store_api.repository;

import com.emil.store_api.entity.Category;
import com.emil.store_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
