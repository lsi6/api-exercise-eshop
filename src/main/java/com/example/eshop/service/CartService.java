package com.example.eshop.service;

import com.example.eshop.db.entities.Cart;
import com.example.eshop.exception.NotFoundException;

import java.util.List;

public interface CartService
{
    /**
     * Method to get all the carts in the database.
     *
     * @return - A list of all the carts in the database
     */
    List<Cart> getAllCarts();

    /**
     * Method to get a cart by ID.
     *
     * @param id - The ID of the cart to fetch
     * @return - The found cart
     * @throws NotFoundException - Thrown when a cart could not be found with the given ID
     */
    Cart getCart(final int id) throws NotFoundException;

    /**
     * Method to save a new cart to the database.
     *
     * @param cart - The cart entity to post.
     * @return - The newly created cart.
     */
    Cart persistCart(final Cart cart);
}
