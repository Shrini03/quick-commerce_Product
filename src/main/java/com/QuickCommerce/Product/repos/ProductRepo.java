package com.QuickCommerce.Product.repos;

import com.QuickCommerce.Product.models.Category;
import com.QuickCommerce.Product.models.Product;
import com.QuickCommerce.Product.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findProductByRestaurant(Restaurant restaurant);
    @Query(value = "select * from Product where category = :category and price <= :maxPrice", nativeQuery = true)
    List<Product> findProductByCategoryAndAndMaxPrice(@Param("category") Category category, @Param("price") Double maxPrice);
}
