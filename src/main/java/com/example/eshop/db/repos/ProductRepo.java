package com.example.eshop.db.repos;

import com.example.eshop.db.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the product table
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>
{}
