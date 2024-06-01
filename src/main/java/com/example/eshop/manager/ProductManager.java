package com.example.eshop.manager;

import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.model.dto.ProductDto;

import java.util.List;

public interface ProductManager
{
    /**
     * Method to get all the products in the database.
     *
     * @return - All the products in the database
     */
    List<ProductDto> getAllProducts();

    /**
     * Method to get a product by ID.
     *
     * @param id - The ID of the product to get
     * @return - The product with the given ID
     */
    ProductDto getProduct(final String id) throws NotFoundException, BadRequestException;

    /**
     * Method to post a new product to the database.
     *
     * @param productDto - The product to post
     * @return - The newly created resource
     */
    ProductDto postProduct(final ProductDto productDto) throws BadRequestException;

    /**
     * Method to delete a product from the database with the given ID.
     *
     * @param id - The ID of the product to delete
     */
    void deleteProduct(final String id) throws BadRequestException;
}
