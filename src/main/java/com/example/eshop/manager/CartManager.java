package com.example.eshop.manager;

import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.model.dto.CartDto;
import com.example.eshop.model.dto.CartProductDto;
import com.example.eshop.model.dto.CheckedOutCartDto;

import java.util.List;

public interface CartManager
{
    /**
     * Method to get all the carts in the database.
     *
     * @return - All the carts in the database
     */
    List<CartDto> getAllCarts();

    /**
     * Method to create an empty cart and add it to the database.
     *
     * @return - The newly created resource
     */
    CartDto createEmptyCart();

    /**
     * Method to add a list of products to a shopping cart
     *
     * @param id - The ID of the shopping cart to modify
     * @param cartProducts - The product list to modify the shopping cart with
     * @return - The modified cart
     * @throws NotFoundException - Thrown if a cart with the given ID cannot be found
     * @throws BadRequestException - Thrown if the request was invalid in some way
     */
    CartDto putCart(final String id, final CartProductDto[] cartProducts) throws BadRequestException, NotFoundException;

    /**
     * Method to post a new cart to the database.
     *
     * @param id - The ID of the cart to check out
     * @return - The checked out cart
     */
    CheckedOutCartDto checkoutCart(final String id) throws BadRequestException, NotFoundException;
}
