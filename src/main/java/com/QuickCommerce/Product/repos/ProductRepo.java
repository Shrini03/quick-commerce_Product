package com.QuickCommerce.Product.repos;

import com.QuickCommerce.Product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
