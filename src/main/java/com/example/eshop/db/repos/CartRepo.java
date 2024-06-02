package com.example.eshop.db.repos;

import com.example.eshop.db.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the cart table
 */
@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>
{}
