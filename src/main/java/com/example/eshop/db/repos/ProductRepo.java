package com.example.eshop.db.repos;

import com.example.eshop.db.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for the product table
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>
{
    @Query("SELECT p from Product p WHERE p.name=?1")
    Product findProductByName(String name);
}
