package com.example.eshop.db.repos;

import com.example.eshop.db.entities.CartProduct;
import com.example.eshop.db.entities.CartProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the cart table
 */
@Repository
public interface CartProductRepo extends JpaRepository<CartProduct, CartProductKey>
{}
