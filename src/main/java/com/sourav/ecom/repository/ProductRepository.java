package com.sourav.ecom.repository;

import com.sourav.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer > {

    @Query("SELECT p from p WHERE "+ "LOWER(p.name) LIKE LOWER(CONCAT('%', :key, '%')) OR " +
    "LOWER(p.description) LIKE LOWER(CONCAT('%', :key, '%')) OR "+
    "LOWER(p.brand) LIKE LOWER(CONCAT('%', :key, '%')) OR " +
    "LOWER(p.category) LIKE LOWER(CONCAT('%', :key, '%')) OR ")
    List<Product> searchProduct(String key);
}
